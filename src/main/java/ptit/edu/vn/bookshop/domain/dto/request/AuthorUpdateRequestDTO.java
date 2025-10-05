package ptit.edu.vn.bookshop.domain.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import ptit.edu.vn.bookshop.domain.constant.GenderEnum;
import ptit.edu.vn.bookshop.domain.constant.StatusEnum;

import java.time.LocalDate;

@Getter
public class AuthorUpdateRequestDTO {
    private String name;
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;
    private String country;
    private String biography;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
}
