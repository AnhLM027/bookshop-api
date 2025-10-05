package ptit.edu.vn.bookshop.domain.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordRequestDTO {
    @NotBlank(message = "New password must not be blank")
    private String newPassword;
    @NotBlank(message = "Confirm new password must not be blank")
    private String confirmNewPassword;
    @NotBlank(message = "ResetToken muset node be blank")
    private String resetToken;
}
