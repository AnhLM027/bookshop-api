package ptit.edu.vn.bookshop.service;

import ptit.edu.vn.bookshop.domain.entity.RedisToken;

public interface RedisTokenService {
    void storeAccessToken(Long userId, String token, long ttlSeconds);
    void storeRefreshToken(Long userId, String token, long ttlSeconds);
}
