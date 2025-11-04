package ptit.edu.vn.bookshop.service.impl;

import org.springframework.stereotype.Service;
import ptit.edu.vn.bookshop.domain.entity.RedisToken;
import ptit.edu.vn.bookshop.repository.RedisTokenRepository;
import ptit.edu.vn.bookshop.service.RedisTokenService;

@Service
public class RedisTokenServiceImpl implements RedisTokenService {
    private final RedisTokenRepository redisTokenRepository;

    public RedisTokenServiceImpl(RedisTokenRepository redisTokenRepository) {
        this.redisTokenRepository = redisTokenRepository;
    }

    @Override
    public void saveToken(RedisToken token) {
        this.redisTokenRepository.save(token);
    }
}
