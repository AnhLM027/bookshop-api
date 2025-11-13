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
public class RevenueByMonthResponse {
    private Integer year;
    private Integer month;
    private BigDecimal totalRevenue;

}
