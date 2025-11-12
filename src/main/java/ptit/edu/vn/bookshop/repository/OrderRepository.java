package ptit.edu.vn.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ptit.edu.vn.bookshop.domain.dto.response.dashboard.RevenueByMonthResponse;
import ptit.edu.vn.bookshop.domain.entity.Order;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> , JpaSpecificationExecutor<Order> {

    @Query("SELECT COUNT(o) FROM Order o WHERE o.status = 'SHIPPED'")
   long countShippedOrders();

    @Query("SELECT COUNT(o) FROM Order o WHERE o.status = 'DELIVERED'")
    long countDeliveredOrders();

    @Query("SELECT SUM(o.totalPrice) FROM Order o WHERE o.status = 'DELIVERED'")
    BigDecimal getTotalPrice();

    @Query("SELECT new ptit.edu.vn.bookshop.domain.dto.response.dashboard.RevenueByMonthResponse(" +
            "FUNCTION('YEAR', o.orderDate), FUNCTION('MONTH', o.orderDate), SUM(o.totalPrice)) " +
            "FROM Order o " +
            "WHERE o.status = ptit.edu.vn.bookshop.domain.constant.OrderStatusEnum.DELIVERED " +
            "GROUP BY FUNCTION('YEAR', o.orderDate), FUNCTION('MONTH', o.orderDate) " +
            "ORDER BY FUNCTION('YEAR', o.orderDate), FUNCTION('MONTH', o.orderDate)")
    List<RevenueByMonthResponse> getRevenueByMonth();






}
