package ptit.edu.vn.bookshop.service;

import ptit.edu.vn.bookshop.dto.request.CheckoutRequestDTO;
import ptit.edu.vn.bookshop.dto.response.CheckoutResponseDTO;

public interface CheckoutService {
    CheckoutResponseDTO getCheckout(CheckoutRequestDTO checkoutRequestDTO);
}
