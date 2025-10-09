package ptit.edu.vn.bookshop.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
public class OrderRequestDTO {
    private List<ItemRequestDTO> cartItems;
    private ShippingAddress shippingAddress;
   // private String paymentMethod;
    private String note;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ShippingAddress {
        private String name;
        private String phone;
        private String street;
        private String ward;
        private String district;
        private String city;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ItemRequestDTO {
        private Long id;
        // ho tro Mua Ngay
        private Long productId;
        private Integer quantity;
    }
}
