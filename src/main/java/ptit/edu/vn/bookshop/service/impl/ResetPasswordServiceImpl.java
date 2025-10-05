package ptit.edu.vn.bookshop.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ptit.edu.vn.bookshop.domain.constant.StatusEnum;
import ptit.edu.vn.bookshop.domain.constant.TokenType;
import ptit.edu.vn.bookshop.domain.dto.request.auth.ForgotPasswordRequestDTO;
import ptit.edu.vn.bookshop.domain.entity.User;
import ptit.edu.vn.bookshop.domain.entity.UserToken;
import ptit.edu.vn.bookshop.repository.UserRepository;
import ptit.edu.vn.bookshop.repository.UserTokenRepository;
import ptit.edu.vn.bookshop.service.EmailService;
import ptit.edu.vn.bookshop.service.ResetPasswordService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {

    private final UserRepository userRepository;
    private final UserTokenRepository userTokenRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public ResetPasswordServiceImpl(UserRepository userRepository,
                                    UserTokenRepository userTokenRepository,
                                    EmailService emailService,
                                    PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userTokenRepository = userTokenRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public String forgotPassword(ForgotPasswordRequestDTO forgotPasswordRequestDTO) {
        User user = userRepository.findByEmail(forgotPasswordRequestDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getStatus().equals(StatusEnum.INACTIVE)) {
            throw new IllegalArgumentException("User account is not active");
        }
        // Xóa OTP cũ nếu còn tồn tại
        this.userTokenRepository.deleteByUserIdAndTokenType(user.getId(), TokenType.OTP);

        // Tạo OTP mới
        String otp = String.format("%06d", new Random().nextInt(999999));
        UserToken otpToken = new UserToken();
        otpToken.setUser(user);
        otpToken.setTokenType(TokenType.OTP);
        otpToken.setTokenValue(otp);
        otpToken.setVerified(false);
        otpToken.setExpiryTime(Instant.now().plus(2, ChronoUnit.MINUTES));
        userTokenRepository.save(otpToken);

        // Gửi email OTP
        Map<String, Object> variables = new HashMap<>();
        variables.put("otp", otp);
        emailService.sendEmailFromTemplateSync(
                user.getEmail(),
                "OTP for Password Reset",
                "otpVerify",
                variables
        );

        return "OTP has been sent to your email address. Please check your inbox.";
    }

    @Override
    public String otpVerification(String otp) {
        UserToken userToken = userTokenRepository.findByTokenValue(otp)
                .orElseThrow(() -> new RuntimeException("Invalid OTP"));

        // Kiểm tra OTP hết hạn
        if (userToken.getExpiryTime() == null || userToken.getExpiryTime().isBefore(Instant.now())) {
            userTokenRepository.delete(userToken);
            throw new RuntimeException("OTP has expired");
        }
        User user = userToken.getUser();
        if (user.getStatus().equals(StatusEnum.INACTIVE)) {
            throw new IllegalArgumentException("User account is not active");
        }
        // Xóa OTP sau khi xác thực thành công
        userTokenRepository.delete(userToken);

        // Tạo ResetToken
        String resetTokenValue = UUID.randomUUID().toString();
        UserToken resetToken = new UserToken();
        resetToken.setUser(user);
        resetToken.setTokenType(TokenType.RESET);
        resetToken.setTokenValue(resetTokenValue);
        resetToken.setVerified(false);
        resetToken.setExpiryTime(Instant.now().plus(10  , ChronoUnit.MINUTES));
        userTokenRepository.save(resetToken);

        return resetTokenValue;
    }

    @Override
    public String resetPassword(String newPassword, String confirmNewPassword, String resetTokenValue) {
        UserToken resetToken = userTokenRepository.findByTokenValue(resetTokenValue)
                .orElseThrow(() -> new RuntimeException("Invalid reset token"));

        if (resetToken.getTokenType() != TokenType.RESET) {
            throw new RuntimeException("Invalid token type for reset password");
        }

        if (resetToken.getExpiryTime() == null || resetToken.getExpiryTime().isBefore(Instant.now())) {
            userTokenRepository.delete(resetToken);
            throw new RuntimeException("Reset token has expired");
        }

        if (!newPassword.equals(confirmNewPassword)) {
            throw new RuntimeException("New password and confirmation do not match");
        }

        User user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        resetToken.setVerified(true);
        userTokenRepository.save(resetToken);

        Map<String, Object> variables = new HashMap<>();
        variables.put("username", user.getName());
        emailService.sendEmailFromTemplateSync(
                user.getEmail(),
                "Password Changed Successfully",
                "resetPasswordNotifition",
                variables
        );

        return "Password has been reset successfully. An email confirmation has been sent.";
    }
}
