package ptit.edu.vn.bookshop.service;

import ptit.edu.vn.bookshop.dto.response.dashboard.OrderStatusStatisticResponse;
import ptit.edu.vn.bookshop.dto.response.dashboard.OverviewResponse;
import ptit.edu.vn.bookshop.dto.response.dashboard.RevenueByMonthResponse;

import java.util.List;

public interface AdminDashboardService {
    OverviewResponse getOverview();
   List<RevenueByMonthResponse> getRevenueByMonth();
   List<OrderStatusStatisticResponse>  getOrderStatusStatistic();
}
