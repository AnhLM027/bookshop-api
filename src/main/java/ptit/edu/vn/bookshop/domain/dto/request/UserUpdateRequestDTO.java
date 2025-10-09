package ptit.edu.vn.bookshop.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import ptit.edu.vn.bookshop.domain.constant.GenderEnum;
import lombok.Getter;
import lombok.Setter;
import ptit.edu.vn.bookshop.domain.constant.StatusEnum;

import java.time.LocalDate;

@Getter
public class UserUpdateRequestDTO {
    private String name;
    private String email;
    private LocalDate dateOfBirth;
    private String phone;
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    private String avatar;
    private UserUpdateRequestDTO.UserRoleUpdateRequestDTO role;

    @Getter
    public static class UserRoleUpdateRequestDTO{
        private Long id;
    }
}
