package ptit.edu.vn.bookshop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ptit.edu.vn.bookshop.domain.dto.request.BookCreateRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.request.BookUpdateRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.response.BookResponseDTO;
import ptit.edu.vn.bookshop.domain.dto.response.page.BookPageResponseDTO;

public interface BookService {
    BookResponseDTO createBook(BookCreateRequestDTO bookRequestDTO);
    BookResponseDTO updateBook(BookUpdateRequestDTO bookRequestDTO, Long id);
    void deleteBook(Long id);
    BookResponseDTO getBook(Long id, boolean isAdmin);
    BookPageResponseDTO fetchAllBooks(Pageable pageable,String[] book, String[] category, String[] author, String[] publisher,boolean isAdmin);
}
