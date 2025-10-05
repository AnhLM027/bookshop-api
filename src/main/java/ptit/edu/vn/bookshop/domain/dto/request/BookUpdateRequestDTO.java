package ptit.edu.vn.bookshop.domain.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import ptit.edu.vn.bookshop.domain.constant.BookStatusEnum;

import java.math.BigDecimal;

@Getter
public class BookUpdateRequestDTO {
    private String name;
    private String title;
    private String description;
    private String language;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal discount;
    @Enumerated(EnumType.STRING)
    private BookStatusEnum status;
    private String image;
    private BookCreateRequestDTO.BookCategoryRequestDTO category;
    private BookCreateRequestDTO.BookAuthorRequestDTO author;
    private BookCreateRequestDTO.BookPublisherRequestDTO publisher;

    @Getter
    @Setter
    public static class BookCategoryRequestDTO {
        private Long id;
    }

    @Getter
    @Setter
    public static class BookAuthorRequestDTO {
        private Long id;
    }

    @Getter
    @Setter
    public static class BookPublisherRequestDTO{
        private Long id;
    }
}
