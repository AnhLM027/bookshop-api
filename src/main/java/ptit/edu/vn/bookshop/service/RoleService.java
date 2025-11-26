package ptit.edu.vn.bookshop.service;

import org.springframework.data.domain.Pageable;
import ptit.edu.vn.bookshop.dto.request.RoleCreateRequestDTO;
import ptit.edu.vn.bookshop.dto.request.RoleUpdateRequestDTO;
import ptit.edu.vn.bookshop.dto.response.page.RolePageResponseDTO;
import ptit.edu.vn.bookshop.dto.response.RoleResponseDTO;

public interface RoleService {
    RoleResponseDTO createRole(RoleCreateRequestDTO roleCreateRequestDTO);
    RoleResponseDTO updateRole(RoleUpdateRequestDTO roleUpdateRequestDTO, Long id);
    RoleResponseDTO fetchRole(Long id);
    void deleteRole(Long id);
    RolePageResponseDTO fetchAllRoles(Pageable pageable, String[] role);
}
