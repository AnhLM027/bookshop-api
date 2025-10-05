package ptit.edu.vn.bookshop.service;

import ptit.edu.vn.bookshop.domain.dto.request.ShippingAddressRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.response.CheckOutResponseDTO;

public interface CheckOutService {
    CheckOutResponseDTO getCheckOutResponseDTO();
    CheckOutResponseDTO updateShippingAddress(ShippingAddressRequestDTO shippingAddress);

}
