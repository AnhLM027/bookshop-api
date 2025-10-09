package ptit.edu.vn.bookshop.service;

import org.springframework.data.domain.Pageable;
import ptit.edu.vn.bookshop.domain.dto.request.RoleRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.response.page.RolePageResponseDTO;
import ptit.edu.vn.bookshop.domain.dto.response.RoleResponseDTO;

public interface RoleService {
    RoleResponseDTO createRole(RoleRequestDTO roleCreateRequestDTO);
    RoleResponseDTO updateRole(RoleRequestDTO roleUpdateRequestDTO, Long id);
    RoleResponseDTO fetchRole(Long id);
    void deleteRole(Long id);
    RolePageResponseDTO fetchAllRoles(Pageable pageable, String[] role);
}
