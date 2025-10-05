package ptit.edu.vn.bookshop.service;

import ptit.edu.vn.bookshop.domain.dto.request.OrderRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.response.OrderResponseDTO;

public interface OrderService {
    OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO);
}
