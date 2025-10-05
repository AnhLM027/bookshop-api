package ptit.edu.vn.bookshop.domain.dto.request;

import ptit.edu.vn.bookshop.domain.constant.GenderEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
public class AuthorCreateRequestDTO {
    private String name;
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;
    private String country;
    private String biography;
}
