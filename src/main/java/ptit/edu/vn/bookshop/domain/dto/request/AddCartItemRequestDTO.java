package ptit.edu.vn.bookshop.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
public class AddCartItemRequestDTO {
    private Long productId;
    @Min(1)
    private int quantity;
}
