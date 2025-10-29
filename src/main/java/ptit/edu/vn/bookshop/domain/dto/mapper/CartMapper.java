package ptit.edu.vn.bookshop.domain.dto.mapper;

import org.springframework.stereotype.Component;
import ptit.edu.vn.bookshop.domain.dto.response.CartResponseDTO;
import ptit.edu.vn.bookshop.domain.entity.Cart;
import ptit.edu.vn.bookshop.domain.entity.CartItem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    public CartResponseDTO.CartItemResponseDTO mapCartItemToResponseDTO(CartItem item) {
        CartResponseDTO.CartItemResponseDTO dto = new CartResponseDTO.CartItemResponseDTO();
        dto.setId(item.getId());
        dto.setProductId(item.getBook().getId());
        dto.setProductName(item.getBook().getName());
        dto.setProductStatus(item.getBook().getStatus());
        dto.setImageUrl(item.getBook().getImage());
        dto.setUnitPrice(item.getUnitPrice());
        dto.setQuantity(item.getQuantity());
        BigDecimal discount = item.getItemDiscount();
        dto.setDiscount(discount);
        BigDecimal discountedPrice;
        if (discount != null && discount.compareTo(BigDecimal.ZERO) > 0) {
            // Tính giá sau khi giảm
            discountedPrice = item.getUnitPrice().multiply(BigDecimal.ONE.subtract(discount));
        } else {
            // Không giảm giá
            discountedPrice = item.getUnitPrice();
        }
        dto.setDiscountedPrice(discountedPrice);
        BigDecimal totalPrice = discountedPrice.multiply(BigDecimal.valueOf(item.getQuantity()));
        dto.setTotalPrice(totalPrice);
        return dto;
    }

    public CartResponseDTO mapCartToResponseDTO(Cart cart) {
        List<CartResponseDTO.CartItemResponseDTO> itemDTOs = cart.getCartItems().stream()
                .sorted(Comparator.comparing(CartItem::getCreatedAt))
                .map(this::mapCartItemToResponseDTO)
                .collect(Collectors.toList());

        CartResponseDTO.CartSummaryDTO summary = new CartResponseDTO.CartSummaryDTO();
        int totalQuantity = itemDTOs.stream().mapToInt(CartResponseDTO.CartItemResponseDTO::getQuantity).sum();
        BigDecimal subtotal = itemDTOs.stream()
                .map(CartResponseDTO.CartItemResponseDTO::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);

        summary.setTotalQuantity(totalQuantity);
        summary.setSubtotal(subtotal);

        CartResponseDTO dto = new CartResponseDTO();
        dto.setId(cart.getId());
//        dto.setStatus(cart.getStatus());
        dto.setCreatedAt(cart.getCreatedAt());
        dto.setUpdatedAt(Instant.now());
        dto.setCartItems(itemDTOs);
        dto.setSummary(summary);
        return dto;
    }

}
