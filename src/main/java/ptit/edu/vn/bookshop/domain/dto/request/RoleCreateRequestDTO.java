package ptit.edu.vn.bookshop.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class RoleCreateRequestDTO {
    private String name;
    private String description;
    private List<RolePermissionRequestDTO> permissions;

    @Getter
    public static class RolePermissionRequestDTO {
        private Long id;
    }
}
