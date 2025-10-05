package ptit.edu.vn.bookshop.service.mapper;

import ptit.edu.vn.bookshop.domain.dto.request.RoleCreateRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.response.RoleResponseDTO;
import ptit.edu.vn.bookshop.domain.entity.Role;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleMapper {

    public Role mapRoleRequestDtoToRole(RoleCreateRequestDTO dto) {
        Role role = new Role();
        role.setName(dto.getName());
        role.setDescription(dto.getDescription());
        return role;
    }

    public RoleResponseDTO mapRoleToRoleResponseDto(Role role) {
        RoleResponseDTO roleResponseDTO = new RoleResponseDTO();
        roleResponseDTO.setId(role.getId());
        roleResponseDTO.setName(role.getName());
        roleResponseDTO.setDescription(role.getDescription());
        roleResponseDTO.setStatus(role.getStatus());
        roleResponseDTO.setCreatedBy(role.getCreatedBy());
        roleResponseDTO.setCreatedAt(role.getCreatedAt());
        if (role.getPermissions() != null) {
            List<RoleResponseDTO.RolePermissionResponseDTO> permissions = role.getPermissions()
                    .stream()
                    .map(p -> {
                        RoleResponseDTO.RolePermissionResponseDTO permission = new RoleResponseDTO.RolePermissionResponseDTO();
                        permission.setId(p.getId());
                        permission.setName(p.getName());
                        return permission;
                    })
                    .collect(Collectors.toList());
            roleResponseDTO.setPermissions(permissions);
        }
        return roleResponseDTO;
    }
}
