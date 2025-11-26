package ptit.edu.vn.bookshop.service;

import org.springframework.data.domain.Pageable;
import ptit.edu.vn.bookshop.dto.request.PermissionCreateRequestDTO;
import ptit.edu.vn.bookshop.dto.request.PermissionUpdateRequestDTO;
import ptit.edu.vn.bookshop.dto.response.page.PermissionPageResponseDTO;
import ptit.edu.vn.bookshop.dto.response.PermissionResponseDTO;



public interface PermissionService {

    PermissionResponseDTO createPermission(PermissionCreateRequestDTO permissionCreateRequestDTO);
    PermissionResponseDTO updatePermission(PermissionUpdateRequestDTO permissionRequestDTO, Long id);
    void  deletePermission(Long id);
    PermissionResponseDTO fetchPermission(Long id);
    PermissionPageResponseDTO getAllPermission(Pageable pageable, String[] permission);
}
