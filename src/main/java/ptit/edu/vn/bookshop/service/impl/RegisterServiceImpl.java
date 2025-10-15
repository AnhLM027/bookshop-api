package ptit.edu.vn.bookshop.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ptit.edu.vn.bookshop.domain.constant.StatusEnum;
import ptit.edu.vn.bookshop.domain.constant.TokenType;
import ptit.edu.vn.bookshop.domain.dto.request.auth.RegisterRequestDTO;
import ptit.edu.vn.bookshop.domain.entity.Role;
import ptit.edu.vn.bookshop.domain.entity.User;
import ptit.edu.vn.bookshop.domain.entity.UserToken;
import ptit.edu.vn.bookshop.exception.UsernameNotFoundException;
import ptit.edu.vn.bookshop.repository.RoleRepository;
import ptit.edu.vn.bookshop.repository.UserRepository;
import ptit.edu.vn.bookshop.repository.UserTokenRepository;
import ptit.edu.vn.bookshop.service.EmailService;
import ptit.edu.vn.bookshop.service.RegisterService;
import ptit.edu.vn.bookshop.mapper.UserMapper;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserTokenRepository userTokenRepository;
    private final UserMapper userMapper;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public RegisterServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserTokenRepository userTokenRepository,
                               UserMapper userMapper, EmailService emailService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userTokenRepository = userTokenRepository;
        this.userMapper = userMapper;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String userRegister(RegisterRequestDTO registerRequestDTO) {
        String password = registerRequestDTO.getPassword();
        String confirmPassword = registerRequestDTO.getConfirmPassword();
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Password and Confirm Password do not match");
        }

        Optional<User> userOptional = this.userRepository.findByEmail(registerRequestDTO.getEmail());
        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            if (user.getStatus().equals(StatusEnum.ACTIVE)) {
                throw new IllegalArgumentException("Email is already registered and active");
            }
            // Nếu INACTIVE → update thông tin mới từ DTO
            user.setName(registerRequestDTO.getName());
            user.setPhone(registerRequestDTO.getPhone());
            user.setPassword(passwordEncoder.encode(password));
            this.userRepository.save(user);
            sendVerificationEmail(user);
            return "Email already exists but inactive. Information updated and verification email resent.";
        }

        // Nếu chưa tồn tại, tạo user mới
        user = this.userMapper.fromRegisterDto(registerRequestDTO);
        user.setStatus(StatusEnum.INACTIVE);
        user.setPassword(passwordEncoder.encode(password));

        Role role = this.roleRepository.findByName("USER")
                .orElseThrow(() -> new UsernameNotFoundException("Role not found"));
        user.setRole(role);

        this.userRepository.save(user);

        sendVerificationEmail(user);

        return "Registration successful. Please check your email to verify your account.";
    }


    private void sendVerificationEmail(User user) {
        Optional<UserToken> existingTokenOpt = this.userTokenRepository
                .findByUserIdAndTokenTypeAndVerifiedFalse(user.getId(), TokenType.VERIFICATION);
        UserToken userToken;
        if (existingTokenOpt.isPresent()) {
            userToken = existingTokenOpt.get();
            if (userToken.getExpiryTime().isAfter(Instant.now())) {
                // Token còn hạn → dùng lại token cũ, gửi lại email
            } else {
                // Token hết hạn → xóa và tạo token mới
                userTokenRepository.delete(userToken);
                userToken = createNewToken(user);
            }
        } else {
            // Không có token → tạo mới
            userToken = createNewToken(user);
        }

        String verifyUrl = "http://localhost:8080/api/v1/auth/verify?token=" + userToken.getTokenValue();
        Map<String, Object> variables = new HashMap<>();
        variables.put("confirmationLink", verifyUrl);

        emailService.sendEmailFromTemplateSync(
                user.getEmail(),
                "Please confirm account.",
                "registerConfirmation",
                variables
        );
    }

    private UserToken createNewToken(User user) {
        UserToken token = new UserToken();
        token.setUser(user);
        token.setTokenType(TokenType.VERIFICATION);
        token.setTokenValue(UUID.randomUUID().toString());
        token.setExpiryTime(Instant.now().plus(10, ChronoUnit.MINUTES));
        token.setVerified(false);
        return userTokenRepository.save(token);
    }

    @Override
    @Transactional
    public String verifyUser(String token) {
        UserToken userToken = this.userTokenRepository.findByTokenValue(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid verification token"));

        if (userToken.getExpiryTime() == null || userToken.getExpiryTime().isBefore(Instant.now())) {
            throw new IllegalArgumentException("Verification token has expired");
        }
        if (userToken.isVerified()) {
            return "Account already verified";
        }
        User user = userToken.getUser();
        user.setStatus(StatusEnum.ACTIVE);

        userToken.setVerified(true);
        this.userTokenRepository.save(userToken);

        this.userRepository.save(user);
        return "Account verified successfully";
    }
}
