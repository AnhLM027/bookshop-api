package ptit.edu.vn.bookshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ptit.edu.vn.bookshop.dto.response.dashboard.OrderStatusStatisticResponse;
import ptit.edu.vn.bookshop.dto.response.dashboard.OverviewResponse;
import ptit.edu.vn.bookshop.dto.response.dashboard.RevenueByMonthResponse;
import ptit.edu.vn.bookshop.service.AdminDashboardService;
import ptit.edu.vn.bookshop.util.anotation.ApiMessage;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DashboardController {

    private final AdminDashboardService adminDashboardService;

    public DashboardController(AdminDashboardService adminDashboardService) {
        this.adminDashboardService = adminDashboardService;
    }

    @GetMapping("/admin/dashboard/overview")
    @ApiMessage("")
    public ResponseEntity<OverviewResponse> getOverview() {
        OverviewResponse response = this.adminDashboardService.getOverview();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/admin/dashboard/statistics/revenue-by-month")
    @ApiMessage("")
    public ResponseEntity<List<RevenueByMonthResponse>> getRevenueByMonth() {
        List<RevenueByMonthResponse> revenueByMonthResponse = this.adminDashboardService.getRevenueByMonth();
        return ResponseEntity.ok().body(revenueByMonthResponse);
    }

    @GetMapping("/admin/dashboard/statistics/orders/status")
    @ApiMessage("")
    public ResponseEntity<List<OrderStatusStatisticResponse>>  getOrderStatusStatistic() {
        List<OrderStatusStatisticResponse> response = this.adminDashboardService.getOrderStatusStatistic();
        return ResponseEntity.ok().body(response);
    }
}
