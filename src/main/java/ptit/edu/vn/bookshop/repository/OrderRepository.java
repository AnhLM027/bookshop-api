package ptit.edu.vn.bookshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ptit.edu.vn.bookshop.domain.dto.response.dashboard.RevenueByMonthResponse;
import ptit.edu.vn.bookshop.domain.entity.Order;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> , JpaSpecificationExecutor<Order> {

    Page<Order> findByUserId(Long userId, Pageable pageable);

    @Query("SELECT SUM(o.totalPrice) FROM Order o WHERE o.status = 'DELIVERED'")
    BigDecimal getTotalPrice();

    @Query("SELECT YEAR(o.orderReceivedDate), MONTH(o.orderReceivedDate), SUM(o.totalPrice) " +
            "FROM Order o " +
            "GROUP BY  YEAR(o.orderReceivedDate), MONTH(o.orderReceivedDate)")
    List<Object[]> getRevenueByMonth();


    @Query("SELECT o.status, COUNT(o) FROM Order o GROUP BY o.status")
    List<Object[]> getOrderStatusStatistic();

}
