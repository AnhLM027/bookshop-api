package ptit.edu.vn.bookshop.service;

import org.springframework.data.domain.Pageable;
import ptit.edu.vn.bookshop.domain.dto.request.CategoryCreateRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.request.CategoryUpdateRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.response.CategoryResponseDTO;
import ptit.edu.vn.bookshop.domain.dto.response.page.CategoryPageResponseDTO;

public interface CategoryService {
    CategoryResponseDTO createCategory(CategoryCreateRequestDTO categoryRequestDTO);
    CategoryResponseDTO updateCategory(CategoryUpdateRequestDTO categoryRequestDTO, Long id);
    CategoryResponseDTO fetchCategory(Long id, boolean isAdmin);
    void deleteCategory(Long id);
    CategoryPageResponseDTO fetchAllCategories(Pageable pageable, String[] category, boolean isAdmin);

}
