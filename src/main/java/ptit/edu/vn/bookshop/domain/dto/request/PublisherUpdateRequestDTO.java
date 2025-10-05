package ptit.edu.vn.bookshop.domain.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import ptit.edu.vn.bookshop.domain.constant.StatusEnum;

@Getter
public class PublisherUpdateRequestDTO {
    private String name;
    private String address;
    private String phone;
    private String email;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
}
