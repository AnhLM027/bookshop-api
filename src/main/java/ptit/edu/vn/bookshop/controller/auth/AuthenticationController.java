package ptit.edu.vn.bookshop.controller.auth;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import ptit.edu.vn.bookshop.domain.dto.request.auth.*;
import ptit.edu.vn.bookshop.domain.dto.response.LoginResponseDTO;
import ptit.edu.vn.bookshop.domain.dto.response.UserResponseDTO;
import ptit.edu.vn.bookshop.exception.BadCredentialsException;
import ptit.edu.vn.bookshop.exception.IdInvalidException;

import ptit.edu.vn.bookshop.service.RedisTokenService;
import ptit.edu.vn.bookshop.service.RegisterService;
import ptit.edu.vn.bookshop.service.ResetPasswordService;
import ptit.edu.vn.bookshop.service.UserService;
import ptit.edu.vn.bookshop.util.anotation.ApiMessage;
import ptit.edu.vn.bookshop.util.security.SecurityUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final SecurityUtil securityUtil;
    private final UserService userService;
    private final RegisterService registerService;
    private final ResetPasswordService resetPasswordService;
    private final RedisTokenService redisTokenService;


    @Value("${app.jwt.refresh-token-validity-in-seconds}")
    private Long refreshTokenExpiration;

    public AuthenticationController(ResetPasswordService resetPasswordService, AuthenticationManager authenticationManager,
                                    SecurityUtil securityUtil, UserService userService, RegisterService registerService,
                                    RedisTokenService redisTokenService) {
        this.authenticationManager = authenticationManager;
        this.securityUtil = securityUtil;
        this.userService = userService;
        this.registerService = registerService;
        this.resetPasswordService = resetPasswordService;
        this.redisTokenService = redisTokenService;
    }

    @PostMapping("/login")
    @ApiMessage("user login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());

        // xac thuc nguoi dung goi den ham loadUserByUsername
        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);

        LoginResponseDTO response = new LoginResponseDTO();
        UserResponseDTO user = this.userService.getUserByEmail(loginRequestDTO.getUsername());

        // Map Role -> UserRoleResponseDTO
        UserResponseDTO.UserRoleResponseDTO roleDTO = new UserResponseDTO.UserRoleResponseDTO(
                user.getRole().getId(),
                user.getRole().getName()
        );

        if (user != null) {
            LoginResponseDTO.UserLogin userLogin = new LoginResponseDTO.UserLogin(user.getId(), user.getName(), user.getEmail(), user.getStatus(), roleDTO);
            response.setUser(userLogin);
        }

        //create access_token
        String access_token = this.securityUtil.createAccessToken(authentication.getName(), response);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        response.setAccessToken(access_token);

        // create refresh_token
        String refresh_token = this.securityUtil.createRefreshToken(loginRequestDTO.getUsername(), response);

        this.redisTokenService.storeAccessToken(user.getId(), access_token, 900);
        this.redisTokenService.storeRefreshToken(user.getId(), refresh_token, 4000);


        this.userService.updateUserToken(refresh_token, loginRequestDTO.getUsername());
        // set cookies
        ResponseCookie resCookies = ResponseCookie
                .from("refresh_token", refresh_token)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(refreshTokenExpiration)
                .build();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, resCookies.toString()).body(response);
    }

    @GetMapping("/refresh")
    @ApiMessage("get refresh")
    public ResponseEntity<LoginResponseDTO> getRefreshToken(@CookieValue(name = "refresh_token", defaultValue = "fake_token") String refreshToken) throws IdInvalidException {
        if (refreshToken.equals("fake_token")) {
            throw new BadCredentialsException("Invalid refresh token");
        }
        // giai ma refreshToken
        Jwt decodeToken = this.securityUtil.checkValidRefreshToken(refreshToken);
        String email = decodeToken.getSubject();

        UserResponseDTO user = this.userService.getUserByRefreshTokenAndEmail(refreshToken, email);
        if (user == null) {
            throw new IdInvalidException("refresh token is invalid");
        }
        LoginResponseDTO response = new LoginResponseDTO();
        LoginResponseDTO.UserLogin userLogin = new LoginResponseDTO.UserLogin(user.getId(), user.getName(), user.getEmail(), user.getStatus(), user.getRole());
        response.setUser(userLogin);

        //create access_token
        String access_token = this.securityUtil.createAccessToken(email, response);
        response.setAccessToken(access_token);

        // create refresh_token
        String new_refresh_token = this.securityUtil.createRefreshToken(email, response);

        this.userService.updateUserToken(new_refresh_token, email);
        ResponseCookie responseCookie = ResponseCookie
                .from("refresh_token", new_refresh_token)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(refreshTokenExpiration)
                .build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, responseCookie.toString()).body(response);
    }

    @PostMapping("/register")
    @ApiMessage("user register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
        String registerUser = this.registerService.userRegister(registerRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUser);
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyAccount(@RequestParam("token") String token) {
        String verified = this.registerService.verifyUser(token);
        return ResponseEntity.ok().body(verified);
    }

    @GetMapping("/account")
    @ApiMessage("get account")
    public ResponseEntity<LoginResponseDTO.UserLogin> getAccount() {
        LoginResponseDTO.UserLogin user = this.userService.getCurrentUserAccount();
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/logout")
    @ApiMessage("user logout")
    public ResponseEntity<Void> logout() {
        String email = SecurityUtil.getCurrentUserLogin().isPresent() ? SecurityUtil.getCurrentUserLogin().get() : "";

        if (email.equals("")) {
            throw new BadCredentialsException("Invalid email");
        }
        // update refreshtoken = null
        this.userService.updateUserToken(null, email);

        ResponseCookie deleteCookie = ResponseCookie
                .from("refresh_token", null)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0)
                .build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, deleteCookie.toString()).body(null);
    }

    @PostMapping("/password-change")
    @ApiMessage("Change password")
    public ResponseEntity<String> passwordChange(@Valid @RequestBody PasswordChangeRequestDTO passwordChangeRequestDTO,
                                                 @AuthenticationPrincipal Jwt jwt) {
        String email = jwt.getSubject();
        this.userService.passwordChange(passwordChangeRequestDTO, email);
        return ResponseEntity.ok().body("Password changed successfully");
    }

    @PostMapping("/forgot-password")
    @ApiMessage("Request password reset")
    public ResponseEntity<String> forgotPassword(@Valid @RequestBody ForgotPasswordRequestDTO forgotPasswordRequestDTO) {
        String response = this.resetPasswordService.forgotPassword(forgotPasswordRequestDTO);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/verify-otp")
    @ApiMessage("OTP verification")
    public ResponseEntity<String> otpVerification(@Valid @RequestBody OtpVerificationRequestDTO request) {
        String response = this.resetPasswordService.otpVerification(request.getOtp());
        return ResponseEntity.ok().body(response);

    }

    @PostMapping("/reset-password")
    @ApiMessage("Reset password")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody ResetPasswordRequestDTO request) {
        String response = this.resetPasswordService.resetPassword(request.getNewPassword(), request.getConfirmNewPassword(), request.getResetToken());
        return ResponseEntity.ok().body(response);
    }


}
