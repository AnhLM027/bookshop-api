package ptit.edu.vn.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ptit.edu.vn.bookshop.domain.entity.Review;
import ptit.edu.vn.bookshop.domain.entity.Book;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByBook(Book book);
}