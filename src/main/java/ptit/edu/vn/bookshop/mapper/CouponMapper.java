package ptit.edu.vn.bookshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import ptit.edu.vn.bookshop.dto.request.CouponCreateRequestDTO;
import ptit.edu.vn.bookshop.dto.request.CouponUpdateRequestDTO;
import ptit.edu.vn.bookshop.dto.response.CouponResponseDTO;
import ptit.edu.vn.bookshop.domain.entity.Coupon;

@Mapper(componentModel = "spring")
public interface CouponMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt",  ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "orders", ignore = true),
            @Mapping(target = "status", ignore = true)
    })
    Coupon toEntity(CouponCreateRequestDTO couponCreateRequestDTO);

    CouponResponseDTO toResponseDTO(Coupon coupon);

    @Mappings({
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "updatedBy", ignore = true)
    })
    void updateCouponFromDto(CouponUpdateRequestDTO dto, @MappingTarget Coupon coupon);

}
