package ptit.edu.vn.bookshop.mapper;

import org.mapstruct.Mapper;
import ptit.edu.vn.bookshop.domain.dto.request.PermissionRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.response.PermissionResponseDTO;
import ptit.edu.vn.bookshop.domain.entity.Permission;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toEntity(PermissionRequestDTO dto);
    PermissionResponseDTO toResponseDTO(Permission permission);
}
