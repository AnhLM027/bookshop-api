package ptit.edu.vn.bookshop.service;

import ptit.edu.vn.bookshop.domain.entity.RedisToken;

public interface RedisTokenService {
    void saveToken(RedisToken token);
    void removeToken(String id);
    RedisToken getTokenById(String id);
    void logout(String refreshToken);
}
