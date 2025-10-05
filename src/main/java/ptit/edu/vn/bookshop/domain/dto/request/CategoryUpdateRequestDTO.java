package ptit.edu.vn.bookshop.domain.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import ptit.edu.vn.bookshop.domain.constant.BookStatusEnum;
import ptit.edu.vn.bookshop.domain.constant.StatusEnum;

import java.math.BigDecimal;

@Getter
public class CategoryUpdateRequestDTO {
    private Long id;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
}
