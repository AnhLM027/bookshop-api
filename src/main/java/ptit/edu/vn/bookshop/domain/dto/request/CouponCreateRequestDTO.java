package ptit.edu.vn.bookshop.domain.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import ptit.edu.vn.bookshop.domain.constant.DiscountTypeEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class CouponCreateRequestDTO {
    @NotNull(message = "code is not null")
    private String code;
    @NotNull(message = "name is not null")
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private DiscountTypeEnum discountType;
    @NotNull(message = "discount value not null")
    private BigDecimal discountValue;
    private BigDecimal minimumOrderAmount;
    private BigDecimal maximumDiscountAmount;
    private BigDecimal usageLimit;
    private BigDecimal usageLimitPerCustomer;
    private LocalDateTime  startsAt;
    private LocalDateTime expiresAt;
}
