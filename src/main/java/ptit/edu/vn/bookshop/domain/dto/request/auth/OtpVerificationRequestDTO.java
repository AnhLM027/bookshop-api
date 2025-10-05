package ptit.edu.vn.bookshop.domain.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtpVerificationRequestDTO {
    @NotBlank(message = "OTP must not be blank")
    private String otp;
}
