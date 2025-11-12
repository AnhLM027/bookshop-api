package ptit.edu.vn.bookshop.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ptit.edu.vn.bookshop.domain.dto.request.ReviewRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.response.ReviewResponseDTO;
import ptit.edu.vn.bookshop.service.ReviewService;
import ptit.edu.vn.bookshop.util.anotation.ApiMessage;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/reviews")
    @ApiMessage("Create new review")
    public ResponseEntity<ReviewResponseDTO> createReview(@Valid @RequestBody ReviewRequestDTO request) {
        ReviewResponseDTO response = reviewService.createReview(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/reviews/book/{bookId}")
    @ApiMessage("Get active reviews by book ID")
    public ResponseEntity<List<ReviewResponseDTO>> getReviewsByBook(@PathVariable Long bookId) {
        List<ReviewResponseDTO> reviews = reviewService.getReviewsByBook(bookId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/admin/reviews/book/{bookId}")
    @ApiMessage("Get all reviews by book ID (for admin)")
    public ResponseEntity<List<ReviewResponseDTO>> getAllReviewsByBookForAdmin(@PathVariable Long bookId) {
        List<ReviewResponseDTO> reviews = reviewService.getAllReviewsByBookForAdmin(bookId);
        return ResponseEntity.ok(reviews);
    }

    @PutMapping("/reviews/{id}")
    @ApiMessage("Update review information")
    public ResponseEntity<ReviewResponseDTO> updateReview(
            @PathVariable Long id,
            @Valid @RequestBody ReviewRequestDTO request) {
        ReviewResponseDTO response = reviewService.updateReview(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/admin/reviews/{id}")
    @ApiMessage("Hard delete review (admin only)")
    public ResponseEntity<Void> hardDeleteReview(@PathVariable Long id) {
        reviewService.hardDeleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/reviews/{id}")
    @ApiMessage("Soft delete review (set status to DELETED)")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
