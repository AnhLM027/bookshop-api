package ptit.edu.vn.bookshop.service.mapper;

import ptit.edu.vn.bookshop.domain.dto.request.CategoryCreateRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.response.CategoryResponseDTO;
import ptit.edu.vn.bookshop.domain.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category mapCategoryRequestDTOtoCategory(CategoryCreateRequestDTO categoryRequestDTO) {
        Category category = new Category();
        category.setName(categoryRequestDTO.getName());
        category.setDescription(categoryRequestDTO.getDescription());
        return category;
    }

    public CategoryResponseDTO mapCategorytoCategoryResponseDTO(Category category) {
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setId(category.getId());
        categoryResponseDTO.setName(category.getName());
        categoryResponseDTO.setDescription(category.getDescription());
        categoryResponseDTO.setStatus(category.getStatus());
        categoryResponseDTO.setCreatedBy(category.getCreatedBy());
        categoryResponseDTO.setCreatedAt(category.getCreatedAt());
        return categoryResponseDTO;
    }
}
