package ptit.edu.vn.bookshop.mapper;


import org.mapstruct.Mapper;
import ptit.edu.vn.bookshop.dto.request.AuthorRequestDTO;
import ptit.edu.vn.bookshop.dto.response.AuthorResponseDTO;
import ptit.edu.vn.bookshop.domain.entity.Author;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author toEntity(AuthorRequestDTO dto);
    AuthorResponseDTO toResponseDTO(Author author);
}

