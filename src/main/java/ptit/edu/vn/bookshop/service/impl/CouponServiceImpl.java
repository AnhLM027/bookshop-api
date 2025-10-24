package ptit.edu.vn.bookshop.service.impl;

;
import org.springframework.stereotype.Service;
import ptit.edu.vn.bookshop.domain.dto.request.CouponCreateRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.request.CouponUpdateRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.response.CouponResponseDTO;
import ptit.edu.vn.bookshop.domain.entity.Coupon;
import ptit.edu.vn.bookshop.exception.IdInvalidException;
import ptit.edu.vn.bookshop.mapper.CouponMapper;
import ptit.edu.vn.bookshop.repository.CouponRepository;
import ptit.edu.vn.bookshop.service.CouponService;

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
        Coupon coupon = this.couponMapper.toEntity(couponRequestDTO);
        return this.couponMapper.toResponseDTO(this.couponRepository.save(coupon));
    }

    @Override
    public CouponResponseDTO updateCoupon(CouponUpdateRequestDTO couponRequestDTO, Long id) {
        Coupon coupon = this.couponRepository.findById(id)
                .orElseThrow(() -> new IdInvalidException("coupon is not found"));
        // Kiểm tra ngày hợp lệ
        if (couponRequestDTO.getExpiresAt() != null && couponRequestDTO.getStartsAt() != null &&
                couponRequestDTO.getExpiresAt().isBefore(couponRequestDTO.getStartsAt())) {
            throw new IllegalArgumentException("Expire date must be after start date");
        }
        this.couponMapper.updateCouponFromDto(couponRequestDTO, coupon);
        return this.couponMapper.toResponseDTO(this.couponRepository.save(coupon));
    }
}
