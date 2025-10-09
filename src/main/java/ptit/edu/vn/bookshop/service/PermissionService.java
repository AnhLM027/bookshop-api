package ptit.edu.vn.bookshop.service;

import org.springframework.data.domain.Pageable;
import ptit.edu.vn.bookshop.domain.dto.request.PermissionRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.response.page.PermissionPageResponseDTO;
import ptit.edu.vn.bookshop.domain.dto.response.PermissionResponseDTO;



public interface PermissionService {

    PermissionResponseDTO createPermission(PermissionRequestDTO permissionRequestDTO);
    PermissionResponseDTO updatePermission(PermissionRequestDTO permissionRequestDTO, Long id);
    void  deletePermission(Long id);
    PermissionResponseDTO fetchPermission(Long id);
    PermissionPageResponseDTO getAllPermission(Pageable pageable, String[] permission);
}
