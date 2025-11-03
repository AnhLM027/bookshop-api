package ptit.edu.vn.bookshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ptit.edu.vn.bookshop.domain.entity.RedisToken;

@Repository
public interface RedisTokenRepository extends CrudRepository<RedisToken, String> {
}
