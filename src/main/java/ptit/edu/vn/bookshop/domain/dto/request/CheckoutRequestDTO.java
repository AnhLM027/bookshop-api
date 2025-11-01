package ptit.edu.vn.bookshop.domain.dto.request;

import lombok.Getter;

@Getter
public class CheckoutRequestDTO {
    private Long addressId;
    private String couponCode;

}
