package ptit.edu.vn.bookshop.service.mapper;


import ptit.edu.vn.bookshop.domain.dto.request.AuthorRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.response.AuthorResponseDTO;
import ptit.edu.vn.bookshop.domain.entity.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public Author mapAuthorRequestDtoToAuthor(AuthorRequestDTO dto) {
        Author author = new Author();
        author.setName(dto.getName());
        author.setDateOfBirth(dto.getDateOfBirth());
        author.setGender(dto.getGender());
        author.setCountry(dto.getCountry());
        author.setBiography(dto.getBiography());
        return author;
    }

    public AuthorResponseDTO mapAuthorToAuthorResponseDTO(Author author) {
        AuthorResponseDTO authorResponseDTO = new AuthorResponseDTO();
        authorResponseDTO.setId(author.getId());
        authorResponseDTO.setName(author.getName());
        authorResponseDTO.setDateOfBirth(author.getDateOfBirth());
        authorResponseDTO.setGender(author.getGender());
        authorResponseDTO.setCountry(author.getCountry());
        authorResponseDTO.setBiography(author.getBiography());
        authorResponseDTO.setStatus(author.getStatus());
        authorResponseDTO.setCreatedAt(author.getCreatedAt());
        authorResponseDTO.setUpdateAt(author.getUpdatedAt());
        return authorResponseDTO;
    }
}
