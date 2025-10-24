package ptit.edu.vn.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ptit.edu.vn.bookshop.domain.entity.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Long> {
    boolean existsByCode(String code);
    Coupon findByCode(String code);
}
