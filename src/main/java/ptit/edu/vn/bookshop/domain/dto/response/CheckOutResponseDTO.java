package ptit.edu.vn.bookshop.domain.dto.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ptit.edu.vn.bookshop.domain.constant.BookStatusEnum;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckOutResponseDTO {
    private ShippingAddress shippingAddress;
    private List<CartResponseDTO.CartItemResponseDTO> items;
//    private List<PaymentMethod> paymentMethods;
    private SummaryCheckout summary;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ShippingAddress {
        private String name;
        private String phone;
        private String address;
    }

//    @Getter
//    @Setter
//    @NoArgsConstructor
//    @AllArgsConstructor
//    public static class PaymentMethod {
//        private String code;
//        private String name;
//    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SummaryCheckout {
        private Integer totalQuantity;
        private BigDecimal subtotal;
        private BigDecimal cartDiscount;
        private BigDecimal shippingFee;
        private BigDecimal grandTotal;
    }
}
