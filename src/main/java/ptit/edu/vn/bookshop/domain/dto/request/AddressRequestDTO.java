package ptit.edu.vn.bookshop.domain.dto.request;

import lombok.Getter;

@Getter
public class AddressRequestDTO {
    private String name;
    private String phone;
    private String street;
    private String ward;
    private String district;
    private String city;
    private Boolean isDefault;
}
