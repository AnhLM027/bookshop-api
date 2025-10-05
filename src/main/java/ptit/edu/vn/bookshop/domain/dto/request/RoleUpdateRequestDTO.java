package ptit.edu.vn.bookshop.domain.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import ptit.edu.vn.bookshop.domain.constant.StatusEnum;

import java.util.List;

@Getter
public class RoleUpdateRequestDTO {
    private String name;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    private String description;
    private List<RoleCreateRequestDTO.RolePermissionRequestDTO> permissions;

    @Getter
    public static class RolePermissionRequestDTO {
        private Long id;
    }
}
