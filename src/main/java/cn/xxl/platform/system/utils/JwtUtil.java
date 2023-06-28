package cn.xxl.platform.system.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author xiaoxiaolong
 * @since 2023/5/29
 */
public final class JwtUtil {
    public static final String SECRET = "ADF!@#DF#3";

    private JwtUtil() {
    }

    public static String generateToken(String username, Long userId) {
        return JWT.create()
                .withClaim("username", username)
                .withClaim("userId", userId)
                .withExpiresAt(Instant.now().plus(1, ChronoUnit.MINUTES))
                .sign(Algorithm.HMAC256(SECRET));
    }

    public static String getUsername(String token) {
        return JWT.decode(token).getClaim("username").asString();
    }

    public static Long getUserId(String token) {
        return JWT.decode(token).getClaim("userId").asLong();
    }

    // 判断token是否有效
    public static boolean verify(String token) {
        try {
            JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
            return true;
        } catch (JWTVerificationException | IllegalArgumentException e) {
            return false;
        }
    }

    public static boolean isExpired(String token) {
        return JWT.decode(token).getExpiresAt().before(new Date());
    }
}
