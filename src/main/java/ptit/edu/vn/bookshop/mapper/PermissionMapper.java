package ptit.edu.vn.bookshop.mapper;

import org.mapstruct.Mapper;
import ptit.edu.vn.bookshop.dto.request.PermissionCreateRequestDTO;
import ptit.edu.vn.bookshop.dto.response.PermissionResponseDTO;
import ptit.edu.vn.bookshop.domain.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toEntity(PermissionCreateRequestDTO dto);
    PermissionResponseDTO toResponseDTO(Permission permission);
}
