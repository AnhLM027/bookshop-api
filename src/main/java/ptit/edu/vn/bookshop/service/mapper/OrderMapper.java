package ptit.edu.vn.bookshop.service.mapper;

import org.springframework.stereotype.Component;
import ptit.edu.vn.bookshop.domain.dto.request.OrderRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.response.OrderResponseDTO;
import ptit.edu.vn.bookshop.domain.entity.Order;
import ptit.edu.vn.bookshop.domain.entity.OrderItem;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {

    public OrderResponseDTO toOrderResponseDTO(Order order) {
        // Shipping Info
        OrderResponseDTO.ShippingInfo shippingInfo = new OrderResponseDTO.ShippingInfo();
        shippingInfo.setReceiverName(order.getReceiverName());
        shippingInfo.setReceiverPhone(order.getReceiverPhone());
        shippingInfo.setReceiverAddress(order.getReceiverAddress());

        // Payment

        // Summary
        OrderResponseDTO.Summary summary = new OrderResponseDTO.Summary();
        summary.setSubtotal(order.getTotalPrice());
        summary.setShippingFee(order.getShippingFee());
        summary.setDiscountFee(order.getDiscountFee());
        summary.setFinalPrice(order.getFinalPrice());

        // Items
        List<OrderResponseDTO.OrderItemResponse> orderItemResponseList = new ArrayList<>();
        for (OrderItem orderItem : order.getOrderItems()) {
            OrderResponseDTO.OrderItemResponse itemResponse = new OrderResponseDTO.OrderItemResponse();

            itemResponse.setId(orderItem.getId());
            itemResponse.setProductId(orderItem.getBook().getId());
            itemResponse.setProductName(orderItem.getBook().getName());
            itemResponse.setImageUrl(orderItem.getBook().getImage());
            itemResponse.setQuantity(orderItem.getQuantity());
            itemResponse.setUnitPrice(orderItem.getBook().getPrice());
            itemResponse.setDiscount(orderItem.getBook().getDiscount());

            BigDecimal discountedPrice = orderItem.getBook().getPrice()
                    .multiply(BigDecimal.ONE.subtract(orderItem.getBook().getDiscount()));
            itemResponse.setDiscountedPrice(discountedPrice);

            BigDecimal totalPrice = discountedPrice.multiply(BigDecimal.valueOf(orderItem.getQuantity()));
            itemResponse.setTotalPrice(totalPrice);

            orderItemResponseList.add(itemResponse);
        }

        // Final response
        OrderResponseDTO responseDTO = new OrderResponseDTO();
        responseDTO.setId(order.getId());
        responseDTO.setStatus(order.getStatus());
        responseDTO.setCreatedAt(order.getCreatedAt() != null ? order.getCreatedAt() : Instant.now());
        responseDTO.setShippingInfo(shippingInfo);
        responseDTO.setSummary(summary);
        responseDTO.setItems(orderItemResponseList);

        return responseDTO;
    }
}
