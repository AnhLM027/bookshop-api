package ptit.edu.vn.bookshop.service.impl;

import org.springframework.stereotype.Service;
import ptit.edu.vn.bookshop.domain.dto.response.dashboard.OverviewResponse;
import ptit.edu.vn.bookshop.domain.dto.response.dashboard.RevenueByMonthResponse;
import ptit.edu.vn.bookshop.repository.BookRepository;
import ptit.edu.vn.bookshop.repository.OrderRepository;
import ptit.edu.vn.bookshop.repository.UserRepository;
import ptit.edu.vn.bookshop.service.AdminDashboardService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminDashboardServiceImpl implements AdminDashboardService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;

    public AdminDashboardServiceImpl(UserRepository userRepository, BookRepository bookRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public OverviewResponse getOverview() {
        long totalUsers = this.userRepository.count();
        long inactiveUsers = this.userRepository.countUserInActive();
        long totalBooks = this.bookRepository.count();
        long outOfStockBooks = this.bookRepository.countBookOutOfStock();
        long totalOrders = this.orderRepository.count();
        long shippingOrders = this.orderRepository.countShippedOrders();
        long deliveredOrders = this.orderRepository.countDeliveredOrders();
        BigDecimal totalPrice = this.orderRepository.getTotalPrice();
        OverviewResponse overviewResponse = new OverviewResponse();
        overviewResponse.setTotalUsers(totalUsers);
        overviewResponse.setInactiveUsers(inactiveUsers);
        overviewResponse.setTotalBooks(totalBooks);
        overviewResponse.setOutOfStockBooks(outOfStockBooks);
        overviewResponse.setTotalOrders(totalOrders);
        overviewResponse.setShippingOrders(shippingOrders);
        overviewResponse.setDeliveryOrders(deliveredOrders);
        overviewResponse.setTotalRevenue(totalPrice);
        return overviewResponse;
    }

    @Override
    public List<RevenueByMonthResponse> getRevenueByMonth() {
        List<RevenueByMonthResponse> revenueByMonth = this.orderRepository.getRevenueByMonth();
        return revenueByMonth;
    }
}
