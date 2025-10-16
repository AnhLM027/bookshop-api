package ptit.edu.vn.bookshop.domain.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import ptit.edu.vn.bookshop.domain.constant.DiscountTypeEnum;
import ptit.edu.vn.bookshop.domain.constant.StatusEnum;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
public class CouponRequestDTO {
    private String code;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private DiscountTypeEnum discountType;
    private BigDecimal discountAmount;
    private BigDecimal minimumOrderAmount;
    private BigDecimal maximumDiscountAmount;
    private BigDecimal usageLimit;
    private BigDecimal usageLimitPerCustomer;
    private LocalDateTime  startsAt;
    private LocalDateTime expiresAt;
}
