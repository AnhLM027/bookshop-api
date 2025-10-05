package ptit.edu.vn.bookshop.domain.dto.request.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordChangeRequestDTO {
    private String currentPassword;
    private String newPassword;
    @JsonProperty("confirm_new_password")
    private String confirmNewPassword;
}
