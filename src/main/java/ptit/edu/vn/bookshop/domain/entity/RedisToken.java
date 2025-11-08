package ptit.edu.vn.bookshop.domain.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("redisToken")
public class RedisToken {
    private Long userId;
    private String accessToken;
    private String refreshToken;;
}
