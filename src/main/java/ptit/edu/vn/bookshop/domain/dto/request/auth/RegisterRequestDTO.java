package ptit.edu.vn.bookshop.domain.dto.request.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDTO {
    private String name;
    private String email;
    private String phone;
    private String password;
    @JsonProperty("confirm-password")
    private String confirmPassword;
}
