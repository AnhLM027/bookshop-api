package ptit.edu.vn.bookshop.domain.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import ptit.edu.vn.bookshop.domain.constant.OrderStatusEnum;

@Getter
public class UpdateStatusRequestDTO {
    @NotNull(message = "Order status is required")
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;
}
