package ptit.edu.vn.bookshop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ptit.edu.vn.bookshop.domain.constant.StatusEnum;
import ptit.edu.vn.bookshop.dto.request.ReviewRequestDTO;
import ptit.edu.vn.bookshop.dto.response.ReviewResponseDTO;
import ptit.edu.vn.bookshop.domain.entity.Book;
import ptit.edu.vn.bookshop.domain.entity.Review;
import ptit.edu.vn.bookshop.domain.entity.User;
import ptit.edu.vn.bookshop.repository.BookRepository;
import ptit.edu.vn.bookshop.repository.ReviewRepository;
import ptit.edu.vn.bookshop.repository.UserRepository;
import ptit.edu.vn.bookshop.service.ReviewService;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Override
    public ReviewResponseDTO createReview(ReviewRequestDTO request) {
        User user = userRepository.findByEmail(request.getCreatedBy())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Review review = new Review();
        review.setUser(user);
        review.setBook(book);
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setStatus(StatusEnum.ACTIVE);
        review.setCreatedAt(Instant.now());
        review.setCreatedBy(request.getCreatedBy());

        review = reviewRepository.save(review);
        return toResponse(review);
    }

    @Override
    public List<ReviewResponseDTO> getReviewsByBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return reviewRepository.findByBookAndStatus(book, StatusEnum.ACTIVE)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewResponseDTO> getAllReviewsByBookForAdmin(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return reviewRepository.findByBook(book)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewResponseDTO updateReview(Long id, ReviewRequestDTO request) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        Review updated = reviewRepository.save(review);
        return toResponse(updated);
    }

    @Override
    public void hardDeleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        reviewRepository.delete(review);
    }

    @Override
    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        review.setStatus(StatusEnum.DELETED);
        reviewRepository.save(review);
    }

    private ReviewResponseDTO toResponse(Review review) {
        ReviewResponseDTO dto = new ReviewResponseDTO();
        dto.setId(review.getId());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setStatus(review.getStatus());
        dto.setCreatedAt(review.getCreatedAt());
        dto.setCreatedBy(review.getCreatedBy());
        dto.setBookId(review.getBook().getId());
        dto.setBookTitle(review.getBook().getName());
        dto.setUserId(review.getUser().getId());
        dto.setUsername(review.getUser().getName());
        return dto;
    }
}
