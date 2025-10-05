package ptit.edu.vn.bookshop.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
public class PermissionCreateRequestDTO {
    private String name;
    private String apiPath;
    private String method;
    private String module;
}
