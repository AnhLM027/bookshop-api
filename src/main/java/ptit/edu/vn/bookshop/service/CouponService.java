package ptit.edu.vn.bookshop.service;

import ptit.edu.vn.bookshop.domain.dto.request.CategoryRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.request.CouponCreateRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.request.CouponUpdateRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.response.CategoryResponseDTO;
import ptit.edu.vn.bookshop.domain.dto.response.CouponResponseDTO;
import ptit.edu.vn.bookshop.domain.entity.Coupon;

public interface CouponService {
    CouponResponseDTO createCoupon(CouponCreateRequestDTO couponRequestDTO);
    CouponResponseDTO updateCoupon(CouponUpdateRequestDTO couponRequestDTO, Long id);
    Coupon getCouponByCode(String code);
}
