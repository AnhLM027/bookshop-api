package ptit.edu.vn.bookshop.domain.dto.request;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ptit.edu.vn.bookshop.domain.constant.StatusEnum;

@Getter
public class PublisherCreateRequestDTO {
    private String name;
    private String address;
    private String phone;
    private String email;
}
