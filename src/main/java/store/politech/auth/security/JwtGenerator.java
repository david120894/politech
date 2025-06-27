package store.politech.auth.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.function.Function;


@Component
public class JwtGenerator {
    public static final Logger logger = LoggerFactory.getLogger(JwtGenerator.class);

    @Value("${security.jwt.secret-key}")
    private String secret;
    @Value("${security.jwt.expiration}")
    private long expiration;

    public String generateToken(Authentication authentication) {

        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        List<String> roles = userPrincipal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration); // 1 day
        return Jwts.builder()
                .subject(userPrincipal.getUsername())
                .claim("roles", roles)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getKey())
                .compact();
    }

    public Key getKey() {
        byte[] encodedKey = Decoders.BASE64.decode(secret);
//        byte[] encodedKey = Base64.getEncoder().encode(secret.getBytes());
        return Keys.hmacShaKeyFor(encodedKey);
    }

    public <T> T getClaims(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(Jwts.parser()
                .verifyWith((SecretKey) getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload());
    }

    public String getUsernameFromJWT(String token) {
        return getClaims(token, Claims::getSubject);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith((SecretKeySpec) getKey())
                    .build()
                    .parseSignedClaims(token).getPayload();
            return true;
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired" + e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token" + e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported" + e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty" + e.getMessage());
        }
        return false;
    }

//    public String refreshToken(Authentication authentication) {
//        try {
//            UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
//            List<String> roles = userPrincipal.getAuthorities().stream()
//                    .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
//
//            Date currentDate = new Date();
//            Date expireDate = new Date(currentDate.getTime() + SecurityConstants.JWT_AUTHENTICATION_REFRESH_EXPIRES_IN);
//            return Jwts.builder()
//                    .subject(userPrincipal.getUsername())
//                    .claim("roles", roles)
//                    .issuedAt(currentDate)
//                    .expiration(expireDate)
//                    .signWith(getKey())
//                    .compact();
//
//        } catch (Exception e) {
//            throw new RuntimeException("Error internal server");
//        }
//    }
}
