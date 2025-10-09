package ptit.edu.vn.bookshop.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
public class OrderRequestDTO {
    private List<ItemRequestDTO> cartItems;
//    private String paymentMethod;
    private String note;


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
