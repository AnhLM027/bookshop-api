package ptit.edu.vn.bookshop.domain.dto.response.dashboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;



public class RevenueByMonthResponse {
    private Integer year;
    private Integer month;
    private BigDecimal totalRevenue;
    // Constructor cần thiết cho JPQL
    public RevenueByMonthResponse(Integer year, Integer month, BigDecimal totalRevenue) {
        this.year = year;
        this.month = month;
        this.totalRevenue = totalRevenue;
    }

    // Getter/Setter
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    public Integer getMonth() { return month; }
    public void setMonth(Integer month) { this.month = month; }

    public BigDecimal getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(BigDecimal totalRevenue) { this.totalRevenue = totalRevenue; }
}
