package ptit.edu.vn.bookshop.repository;

//public interface UserTokenRepository extends JpaRepository<UserToken, Long> {
//    Optional<UserToken> findByTokenValue(String tokenValue);
//    void deleteByUserIdAndTokenType(Long userId, TokenType tokenType);
//    void deleteByExpiryTimeBefore(Instant now);
//    Optional<UserToken> findByUserIdAndTokenTypeAndVerifiedFalse(Long userId, TokenType tokenType);
//
//    @Modifying
//    @Transactional
//    @Query("DELETE FROM UserToken ut WHERE ut.verified = false AND ut.createdAt < :threshold")
//    void deleteUnverifiedTokensOlderThan(@Param("threshold") Instant threshold);
//}
