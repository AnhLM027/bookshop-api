package ptit.edu.vn.bookshop.service.impl;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ptit.edu.vn.bookshop.domain.entity.RedisToken;
import ptit.edu.vn.bookshop.repository.RedisTokenRepository;
import ptit.edu.vn.bookshop.service.RedisTokenService;

import java.util.concurrent.TimeUnit;

@Service
public class RedisTokenServiceImpl implements RedisTokenService {

    private final RedisTokenRepository redisTokenRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private static final String ACCESS_PREFIX = "ACCESS_TOKEN:";
    private static final String REFRESH_PREFIX = "REFRESH_TOKEN:";


    //    @Override
//    public void saveToken(RedisToken token) {
//        if (token == null || token.getId() == null) {
//            throw new IllegalArgumentException("Token or Token ID must not be null");
//        }
//        this.redisTokenRepository.save(token);
//    }
    public RedisTokenServiceImpl(RedisTokenRepository redisTokenRepository, RedisTemplate<String, String> redisTemplate) {
        this.redisTokenRepository = redisTokenRepository;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void storeAccessToken(Long userId, String token, long ttlSeconds) {
        redisTemplate.opsForValue().set(ACCESS_PREFIX + userId, token, ttlSeconds, TimeUnit.SECONDS);
//        public void removeToken (String id){
//            if (this.redisTokenRepository.existsById(id)) {
//                this.redisTokenRepository.deleteById(id);
//                log.info("Removed token with id {}", id);
//            } else {
//                log.warn("Token id {} not found when trying to remove", id);
//            }
    }

    @Override
    public void saveToken(RedisToken token) {

    }

    @Override
    public void removeToken(String id) {

    }

    @Override
    public RedisToken getTokenById(String id) {
        return null;
    }

    @Override
    public void logout(String refreshToken) {

    }

//
}
