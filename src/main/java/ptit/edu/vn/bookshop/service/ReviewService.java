package ptit.edu.vn.bookshop.service;

import ptit.edu.vn.bookshop.domain.dto.request.ReviewRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.response.ReviewResponseDTO;

import java.util.List;

public interface ReviewService {
    ReviewResponseDTO createReview(ReviewRequestDTO request);
    List<ReviewResponseDTO> getReviewsByBook(Long bookId);
    ReviewResponseDTO updateReview(Long id, ReviewRequestDTO request);
    void deleteReview(Long id);
}