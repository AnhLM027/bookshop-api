package ptit.edu.vn.bookshop.dto.response.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ptit.edu.vn.bookshop.dto.response.OrderResponseDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderPageResponseDTO extends PageResponseAbstractDTO {
    List<OrderResponseDTO> orders;

}
