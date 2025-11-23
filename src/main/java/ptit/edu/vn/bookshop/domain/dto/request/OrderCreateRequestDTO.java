package ptit.edu.vn.bookshop.domain.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
public class OrderCreateRequestDTO {
    @NotEmpty(message = "Cart items cannot be empty")
    private List<ItemRequestDTO> cartItems;
    @Size(max = 50, message = "Coupon code must be at most 50 characters")
    private String couponCode;
    @Size(max = 255, message = "Note must be at most 255 characters")
    private String note;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ItemRequestDTO {
        @NotNull(message = "Item id cannot be null")
        private Long id;
    }
}
