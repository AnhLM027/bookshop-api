package ptit.edu.vn.bookshop.service.mapper;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import ptit.edu.vn.bookshop.domain.dto.response.AddressResponseDTO;
import ptit.edu.vn.bookshop.domain.entity.Address;

@Component
public class AddressMapper {
    public AddressResponseDTO toDto(Address address) {
        AddressResponseDTO dto = new AddressResponseDTO();
        dto.setId(address.getId());
        dto.setName(address.getReceiverName());
        dto.setPhone(address.getPhone());
        dto.setStreet(address.getStreet());
        dto.setWard(address.getWard());
        dto.setDistrict(address.getDistrict());
        dto.setCity(address.getCity());
//        dto.setProvince(address.getProvince());
//        dto.setPostalCode(address.getPostalCode());
        dto.setIsDefault(address.getIsDefault());
        dto.setStatus(address.getStatus());
        dto.setCreatedAt(address.getCreatedAt());
        dto.setUpdateAt(address.getUpdatedAt());
        return dto;
    }
}
