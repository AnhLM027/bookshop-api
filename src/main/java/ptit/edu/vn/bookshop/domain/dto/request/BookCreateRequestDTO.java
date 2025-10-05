package ptit.edu.vn.bookshop.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
public class BookCreateRequestDTO {
    private String name;
    private String title;
    private String description;
    private String language;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal discount;
    private String image;
    private BookCategoryRequestDTO category;
    private BookAuthorRequestDTO author;
    private BookPublisherRequestDTO publisher;

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
