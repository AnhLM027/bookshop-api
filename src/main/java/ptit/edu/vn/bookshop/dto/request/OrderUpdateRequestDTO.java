package ptit.edu.vn.bookshop.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class OrderUpdateRequestDTO {
    @NotBlank(message = "Receiver name cannot be empty")
    @Size(max = 100, message = "Receiver name must be at most 100 characters")
    private String receiverName;

    @NotBlank(message = "Receiver phone cannot be empty")
    @Pattern(
            regexp = "^(0[0-9]{9})$",
            message = "Receiver phone must be a valid Vietnamese phone number"
    )
    private String receiverPhone;

    @NotBlank(message = "City cannot be empty")
    @Size(max = 100, message = "City must be at most 100 characters")
    private String city;

    @NotBlank(message = "District cannot be empty")
    @Size(max = 100, message = "District must be at most 100 characters")
    private String district;

    @NotBlank(message = "Ward cannot be empty")
    @Size(max = 100, message = "Ward must be at most 100 characters")
    private String ward;

    @NotBlank(message = "Street cannot be empty")
    @Size(max = 255, message = "Street must be at most 255 characters")
    private String street;

    @Size(max = 255, message = "Note must be at most 255 characters")
    private String note;
}
