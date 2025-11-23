package ptit.edu.vn.bookshop.domain.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
public class CheckoutRequestDTO {
    @NotEmpty(message = "Cart items cannot be empty")
    private List<CheckoutCartItemsRequestDTO> cartItems;

    @Size(max = 50, message = "Coupon code must be at most 50 characters")
    private String couponCode;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CheckoutCartItemsRequestDTO {

        @NotNull(message = "Item id cannot be null")
        private Long id;
    }
}
