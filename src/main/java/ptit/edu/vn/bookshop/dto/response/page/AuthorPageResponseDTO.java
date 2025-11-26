package ptit.edu.vn.bookshop.dto.response.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ptit.edu.vn.bookshop.dto.response.AuthorResponseDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorPageResponseDTO extends PageResponseAbstractDTO {
    List<AuthorResponseDTO> authors;
}
