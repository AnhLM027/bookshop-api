package ptit.edu.vn.bookshop.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ptit.edu.vn.bookshop.domain.constant.TokenType;
import ptit.edu.vn.bookshop.util.security.SecurityUtil;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "user_tokens")
public class UserToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token_type")
    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    @Column(name = "token_value")
    private String tokenValue;

    @Column(name = "expiry_time")
    private Instant expiryTime;

    @Column(name = "verified")
    private boolean verified;

    @Column(name = "created_at")
    private Instant createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @PrePersist
    public void handleBeforeCreate() {
        this.createdAt = Instant.now();
    }
}
