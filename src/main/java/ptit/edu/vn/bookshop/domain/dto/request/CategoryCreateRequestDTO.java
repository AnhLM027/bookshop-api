package ptit.edu.vn.bookshop.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
public class CategoryCreateRequestDTO {
    private Long id;
    private String name;
    private String description;

}
