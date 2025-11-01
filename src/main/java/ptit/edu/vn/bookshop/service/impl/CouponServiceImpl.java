package ptit.edu.vn.bookshop.service.impl;

;
import org.springframework.stereotype.Service;
import ptit.edu.vn.bookshop.domain.dto.request.CouponCreateRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.request.CouponUpdateRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.response.CouponResponseDTO;
import ptit.edu.vn.bookshop.domain.entity.Coupon;
import ptit.edu.vn.bookshop.exception.IdInvalidException;
import ptit.edu.vn.bookshop.domain.dto.mapper.CouponMapper;
import ptit.edu.vn.bookshop.repository.CouponRepository;
import ptit.edu.vn.bookshop.service.CouponService;

import java.math.BigDecimal;

@Service
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final CouponMapper couponMapper;

    public CouponServiceImpl(CouponRepository couponRepository, CouponMapper couponMapper) {
        this.couponRepository = couponRepository;
        this.couponMapper = couponMapper;
    }

    @Override
    public CouponResponseDTO createCoupon(CouponCreateRequestDTO couponRequestDTO) {
        // Kiểm tra code trùng
        if (couponRepository.existsByCode(couponRequestDTO.getCode())) {
            throw new IllegalArgumentException("Coupon code already exists");
        }
        // Kiểm tra ngày hợp lệ
        if (couponRequestDTO.getExpiresAt() != null && couponRequestDTO.getStartsAt() != null &&
                couponRequestDTO.getExpiresAt().isBefore(couponRequestDTO.getStartsAt())) {
            throw new IllegalArgumentException("Expire date must be after start date");
        }
        //  Kiểm tra kiểu giảm giá
        if (couponRequestDTO.getDiscountType() == null) {
            throw new IllegalArgumentException("Discount type is required");
        }
        switch (couponRequestDTO.getDiscountType()) {
            case PERCENTAGE -> {
                if (couponRequestDTO.getDiscountValue() == null ||
                        couponRequestDTO.getDiscountValue().compareTo(BigDecimal.ZERO) <= 0 ||
                        couponRequestDTO.getDiscountValue().compareTo(BigDecimal.valueOf(100)) > 0) {
                    throw new IllegalArgumentException("Percentage discount must be between 0 and 100");
                }
            }
            case FIXED_AMOUNT -> {
                if (couponRequestDTO.getDiscountValue() == null ||
                        couponRequestDTO.getDiscountValue().compareTo(BigDecimal.ZERO) <= 0) {
                    throw new IllegalArgumentException("Fixed amount discount must be greater than 0");
                }
            }
            default -> throw new IllegalArgumentException("Invalid discount type");
        }
        Coupon coupon = this.couponMapper.toEntity(couponRequestDTO);
        return this.couponMapper.toResponseDTO(this.couponRepository.save(coupon));
    }


    @Override
    public CouponResponseDTO updateCoupon(CouponUpdateRequestDTO couponRequestDTO, Long id) {
        Coupon coupon = this.couponRepository.findById(id)
                .orElseThrow(() -> new IdInvalidException("Coupon is not found"));
        //  Kiểm tra ngày hợp lệ
        if (couponRequestDTO.getExpiresAt() != null && couponRequestDTO.getStartsAt() != null &&
                couponRequestDTO.getExpiresAt().isBefore(couponRequestDTO.getStartsAt())) {
            throw new IllegalArgumentException("Expire date must be after start date");
        }
        //  Kiểm tra kiểu giảm giá
        if (couponRequestDTO.getDiscountType() != null) {
            switch (couponRequestDTO.getDiscountType()) {
                case PERCENTAGE -> {
                    if (couponRequestDTO.getDiscountValue() == null ||
                            couponRequestDTO.getDiscountValue().compareTo(BigDecimal.ZERO) <= 0 ||
                            couponRequestDTO.getDiscountValue().compareTo(BigDecimal.valueOf(100)) > 0) {
                        throw new IllegalArgumentException("Percentage discount must be between 0 and 100");
                    }
                }
                case FIXED_AMOUNT -> {
                    if (couponRequestDTO.getDiscountValue() == null ||
                            couponRequestDTO.getDiscountValue().compareTo(BigDecimal.ZERO) <= 0) {
                        throw new IllegalArgumentException("Fixed amount discount must be greater than 0");
                    }
                }
                default -> throw new IllegalArgumentException("Invalid discount type");
            }
        }

        this.couponMapper.updateCouponFromDto(couponRequestDTO, coupon);
        return this.couponMapper.toResponseDTO(this.couponRepository.save(coupon));
    }

    @Override
    public Coupon getCouponByCode(String code) {
        return this.couponRepository.findByCode(code).orElseThrow(() -> new IdInvalidException("coupon not found"));
    }

}
