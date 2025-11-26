package ptit.edu.vn.bookshop.mapper;

import org.mapstruct.Mapper;
import ptit.edu.vn.bookshop.dto.request.CategoryRequestDTO;
import ptit.edu.vn.bookshop.dto.response.CategoryResponseDTO;
import ptit.edu.vn.bookshop.domain.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryRequestDTO dto);
    CategoryResponseDTO toResponseDTO(Category category);
}
