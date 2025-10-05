package ptit.edu.vn.bookshop.service.mapper;

import ptit.edu.vn.bookshop.domain.dto.request.PermissionCreateRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.response.PermissionResponseDTO;
import ptit.edu.vn.bookshop.domain.entity.Permission;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapper {
    public Permission mapperPermissionRequestDtoToPerMission(PermissionCreateRequestDTO dto) {
        Permission permission = new Permission();
        permission.setName(dto.getName());
        permission.setModule(dto.getModule());
        permission.setApiPath(dto.getApiPath());
        permission.setMethod(dto.getMethod());
        return permission;
    }

    public  PermissionResponseDTO mapperPermissionToPermissionResponse(Permission entity) {
        PermissionResponseDTO permissionResponseDTO = new PermissionResponseDTO();
        permissionResponseDTO.setId(entity.getId());
        permissionResponseDTO.setName(entity.getName());
        permissionResponseDTO.setModule(entity.getModule());
        permissionResponseDTO.setApiPath(entity.getApiPath());
        permissionResponseDTO.setMethod(entity.getMethod());
        permissionResponseDTO.setStatus(entity.getStatus());
        permissionResponseDTO.setCreatedBy(entity.getCreatedBy());
        permissionResponseDTO.setCreatedAt(entity.getCreatedAt());
        return permissionResponseDTO;
    }

}
