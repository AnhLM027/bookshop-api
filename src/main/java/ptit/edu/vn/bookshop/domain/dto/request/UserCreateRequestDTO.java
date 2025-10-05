package ptit.edu.vn.bookshop.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import ptit.edu.vn.bookshop.domain.constant.GenderEnum;
import lombok.Getter;
import lombok.Setter;
import ptit.edu.vn.bookshop.domain.constant.StatusEnum;

import java.time.LocalDate;

@Getter
public class UserCreateRequestDTO {
    private String name;
    private String email;
    private String password;
    private String address;
    private String phone;
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    private String avatar;
    private UserRoleCreateRequestDTO role;

    @Getter
    public static class UserRoleCreateRequestDTO{
        private Long id;
    }
}
