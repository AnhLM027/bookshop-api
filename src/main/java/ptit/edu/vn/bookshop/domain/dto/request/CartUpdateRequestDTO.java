package ptit.edu.vn.bookshop.domain.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Getter;

@Getter
public class CartUpdateRequestDTO {
    @Min(1)
    private int quantity;
}
