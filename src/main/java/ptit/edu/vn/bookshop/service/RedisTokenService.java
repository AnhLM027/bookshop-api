package ptit.edu.vn.bookshop.service;

import ptit.edu.vn.bookshop.domain.entity.RedisToken;

public interface RedisTokenService {
    void saveToken(RedisToken token);
}
