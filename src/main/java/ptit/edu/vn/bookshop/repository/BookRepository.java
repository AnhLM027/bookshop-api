package ptit.edu.vn.bookshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ptit.edu.vn.bookshop.domain.entity.Author;
import ptit.edu.vn.bookshop.domain.entity.Book;
import ptit.edu.vn.bookshop.domain.entity.Publisher;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    boolean existsByNameAndAuthorAndPublisher(String book, Author author, Publisher publisher);

    @Query("SELECT COUNT(b) FROM Book b WHERE b.status = 'OUT_OF_SHOCK'")
    long countBookOutOfStock();

    @Query("SELECT b FROM Book b ORDER BY b.createdAt DESC ")
    Page<Book> findNewBooks(Pageable pageable);


    @Query("SELECT b FROM Book b ORDER BY b.discount DESC")
    Page<Book> findBooksByHighestDiscount(Pageable pageable);
}
