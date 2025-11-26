package ptit.edu.vn.bookshop.mapper;

import org.mapstruct.Mapper;
import ptit.edu.vn.bookshop.dto.response.AddressResponseDTO;
import ptit.edu.vn.bookshop.domain.entity.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressResponseDTO toDto(Address address);
}
