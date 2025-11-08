package ptit.edu.vn.bookshop.service.impl;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import ptit.edu.vn.bookshop.service.RedisTokenService;

import java.util.concurrent.TimeUnit;

@Service
public class RedisTokenServiceImpl implements RedisTokenService {

//    private final RedisTokenRepository redisTokenRepository;

    private static final String ACCESS_PREFIX = "ACCESS_TOKEN:";
    private static final String REFRESH_PREFIX = "REFRESH_TOKEN:";

    private final RedisTemplate<String, String> redisTemplate;

    public RedisTokenServiceImpl( RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void storeAccessToken(Long userId, String token, long ttlSeconds) {
        redisTemplate.opsForValue().set(ACCESS_PREFIX + userId, token, ttlSeconds, TimeUnit.SECONDS);
    }

    @Override
    public void storeRefreshToken(Long userId, String token, long ttlSeconds) {
        redisTemplate.opsForValue().set(REFRESH_PREFIX + userId, token, ttlSeconds,TimeUnit.SECONDS);
    }
}
