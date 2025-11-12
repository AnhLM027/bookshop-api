package ptit.edu.vn.bookshop.service;

import ptit.edu.vn.bookshop.domain.dto.request.ReviewRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.response.ReviewResponseDTO;

import java.util.List;

public interface ReviewService {
    ReviewResponseDTO createReview(ReviewRequestDTO request);
    List<ReviewResponseDTO> getReviewsByBook(Long bookId);
    List<ReviewResponseDTO> getAllReviewsByBookForAdmin(Long bookId);
    ReviewResponseDTO updateReview(Long id, ReviewRequestDTO request);
    void hardDeleteReview(Long id);
    void deleteReview(Long id);
}
