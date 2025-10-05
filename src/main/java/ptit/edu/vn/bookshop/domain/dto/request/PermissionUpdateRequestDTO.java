package ptit.edu.vn.bookshop.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import ptit.edu.vn.bookshop.domain.constant.StatusEnum;

@Getter
public class PermissionUpdateRequestDTO {
    private String name;
    private String apiPath;
    private String method;
    private String module;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
}
