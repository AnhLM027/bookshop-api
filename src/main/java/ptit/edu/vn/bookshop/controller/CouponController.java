package ptit.edu.vn.bookshop.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ptit.edu.vn.bookshop.domain.dto.request.CouponCreateRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.request.CouponUpdateRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.response.CouponResponseDTO;
import ptit.edu.vn.bookshop.service.CouponService;
import ptit.edu.vn.bookshop.util.anotation.ApiMessage;

@RestController
@RequestMapping("/api/v1")
public class CouponController {

    private CouponService couponService;
    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping("/coupons")
    @ApiMessage("")
    public ResponseEntity<CouponResponseDTO> createCoupon(
            @Valid @RequestBody CouponCreateRequestDTO couponRequestDTO) {
        CouponResponseDTO couponResponse = this.couponService.createCoupon(couponRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(couponResponse);
    }

    @PutMapping("/coupons/{id}")
    @ApiMessage("")
    public ResponseEntity<CouponResponseDTO> updateCoupon(
            @Valid @RequestBody CouponUpdateRequestDTO couponRequestDTO,
            @PathVariable Long id) {
        CouponResponseDTO couponResponse = this.couponService.updateCoupon(couponRequestDTO, id);
        return ResponseEntity.ok().body(couponResponse);
    }
}
