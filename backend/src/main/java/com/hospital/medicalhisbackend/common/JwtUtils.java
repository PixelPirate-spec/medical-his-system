package com.hospital.medicalhisbackend.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;

public class JwtUtils {

    // 密钥，生产环境中应该放在配置文件里
    private static final String SECRET = "medical-his-system-super-secret-key";

    // 过期时间：24小时
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;

    /**
     * 生成 JWT Token
     *
     * @param userId   用户ID
     * @param roleName 角色名
     * @return Token 字符串
     */
    public static String generateToken(Long userId, String roleName) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        return JWT.create()
                .withClaim("userId", userId)
                .withClaim("roleName", roleName)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    /**
     * 校验并解析 Token
     *
     * @param token JWT字符串
     * @return DecodedJWT 对象，可以通过其获取 claims
     */
    public static DecodedJWT verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(token);
        } catch (Exception e) {
            return null;
        }
    }
}
