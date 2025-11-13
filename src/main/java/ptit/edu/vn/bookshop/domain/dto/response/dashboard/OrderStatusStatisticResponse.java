package ptit.edu.vn.bookshop.domain.dto.response.dashboard;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ptit.edu.vn.bookshop.domain.constant.OrderStatusEnum;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusStatisticResponse {
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum orderStatus;
    private Long count;
}
