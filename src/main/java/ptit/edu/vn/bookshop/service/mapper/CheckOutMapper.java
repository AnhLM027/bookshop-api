package ptit.edu.vn.bookshop.service.mapper;

import org.springframework.stereotype.Component;
import ptit.edu.vn.bookshop.domain.dto.response.CartResponseDTO;
import ptit.edu.vn.bookshop.domain.dto.response.CheckOutResponseDTO;
import ptit.edu.vn.bookshop.domain.entity.User;


@Component
public class CheckOutMapper {
    public CheckOutResponseDTO toCheckOutResponseDTO(User user, CartResponseDTO cartResponseDTO) {
        CheckOutResponseDTO.ShippingAddress shippingAddress = new CheckOutResponseDTO.ShippingAddress();
        shippingAddress.setName(user.getName());
        shippingAddress.setPhone(user.getPhone());
        shippingAddress.setAddress(user.getAddress());

        CheckOutResponseDTO.SummaryCheckout summaryCheckout = new CheckOutResponseDTO.SummaryCheckout();
        summaryCheckout.setSubtotal(cartResponseDTO.getSummary().getSubtotal());
        summaryCheckout.setTotalQuantity(cartResponseDTO.getSummary().getTotalQuantity());

        CheckOutResponseDTO checkOutResponseDTO = new CheckOutResponseDTO();
        checkOutResponseDTO.setShippingAddress(shippingAddress);
        checkOutResponseDTO.setItems(cartResponseDTO.getCartItems());
        checkOutResponseDTO.setSummary(summaryCheckout);

        return checkOutResponseDTO;
    }
}
