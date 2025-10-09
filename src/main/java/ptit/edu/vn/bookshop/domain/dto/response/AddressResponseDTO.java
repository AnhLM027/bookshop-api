package ptit.edu.vn.bookshop.domain.dto.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ptit.edu.vn.bookshop.domain.constant.StatusEnum;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponseDTO {
    private Long id;
    private String name;
    private String phone;
    private String street;
    private String ward;
    private String district;
    private String city;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    private Boolean isDefault;
}
