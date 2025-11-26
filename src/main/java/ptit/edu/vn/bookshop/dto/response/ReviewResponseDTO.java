package ptit.edu.vn.bookshop.dto.response;

import lombok.Getter;
import lombok.Setter;
import ptit.edu.vn.bookshop.domain.constant.StatusEnum;

import java.time.Instant;

@Getter
@Setter
public class ReviewResponseDTO {
    private Long id;
    private int rating;
    private String comment;
    private StatusEnum status;
    private Instant createdAt;
    private String createdBy;
    private Long bookId;
    private String bookTitle;
    private Long userId;
    private String username;
}