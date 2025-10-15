package ptit.edu.vn.bookshop.domain.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import ptit.edu.vn.bookshop.domain.constant.StatusEnum;

import java.util.List;

@Getter
public class RoleRequestDTO {

    @NotBlank(message = "Role name is required")
    @Size(min = 2, max = 50, message = "Role name must be between 2 and 50 characters")
    @Pattern(
            regexp = "^[A-Za-z_][A-Za-z0-9_]*$",
            message = "Role name can only contain letters, numbers and underscores, and must start with a letter"
    )
    private String name;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Size(max = 255, message = "Description must not exceed 255 characters")
    @Pattern(
            regexp = "^[A-Za-zÀ-ỹ0-9\\s.,!?@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|<>/~`]*$",
            message = "Description contains invalid characters"
    )
    private String description;

    @NotNull(message = "Permissions list is required")
    @Size(min = 1, message = "At least one permission is required")
    @Valid
    private List<RolePermissionRequestDTO> permissions;

    @Getter
    @Setter
    public static class RolePermissionRequestDTO {

        @NotNull(message = "Permission ID is required")
        @Min(value = 1, message = "Permission ID must be a positive number")
        private Long id;
    }
}