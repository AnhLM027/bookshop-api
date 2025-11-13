package ptit.edu.vn.bookshop.domain.dto.response.dashboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OverviewResponse {
    private long totalUsers;
    private long inactiveUsers;
    private long totalBooks;
    private long outOfStockBooks;
    private long totalOrders;
    private BigDecimal totalRevenue;
}
