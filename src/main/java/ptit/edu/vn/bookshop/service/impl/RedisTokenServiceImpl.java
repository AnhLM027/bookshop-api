package ptit.edu.vn.bookshop.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ptit.edu.vn.bookshop.domain.entity.RedisToken;
import ptit.edu.vn.bookshop.exception.BadCredentialsException;
import ptit.edu.vn.bookshop.exception.IdInvalidException;
import ptit.edu.vn.bookshop.repository.RedisTokenRepository;
import ptit.edu.vn.bookshop.service.RedisTokenService;

@Slf4j
@Service
public class RedisTokenServiceImpl implements RedisTokenService {
    private final RedisTokenRepository redisTokenRepository;

    public RedisTokenServiceImpl(RedisTokenRepository redisTokenRepository) {
        this.redisTokenRepository = redisTokenRepository;
    }

    @Override
    public void saveToken(RedisToken token) {
        if (token == null || token.getId() == null) {
            throw new IllegalArgumentException("Token or Token ID must not be null");
        }
        this.redisTokenRepository.save(token);
    }


    @Override
    public void removeToken(String id) {
        if(this.redisTokenRepository.existsById(id)) {
            this.redisTokenRepository.deleteById(id);
            log.info("Removed token with id {}", id);
        } else {
            log.warn("Token id {} not found when trying to remove", id);
        }
    }

    @Override
    public RedisToken getTokenById(String id) {
        return this.redisTokenRepository.findById(id)
                .orElseThrow(() -> new IdInvalidException("Token not found"));
    }

    @Override
    public void logout(String refreshToken) {
        RedisToken token = this.redisTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new BadCredentialsException("Invalid token"));

        // Xóa token theo UUID
        this.removeToken(token.getId());
    }

}
