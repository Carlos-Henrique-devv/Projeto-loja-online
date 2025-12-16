package br.com.carlos.api.security;

import br.com.carlos.api.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

public class TokenUtil {

    private static final String HEADER = "Authorization";
    private static final String PREFIX = "Bearer ";
    private static final long EXPIRATION = 12 * 60 * 60 * 1000;
    private static final String SECRET_KEY = "AKFJBDJDWOPLÇLOQWERTYUIOPNNFNFDFUIF";
    private static final String EMISSOR = "Carlos Henrique";

    private static Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public static String createToken(Usuario usuario) {
        return PREFIX + Jwts.builder()
                .setSubject(usuario.getEmail())
                .setIssuer(EMISSOR)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public static Authentication validate(HttpServletRequest request) {
        try {
            String token = request.getHeader(HEADER);

            if (token == null || !token.startsWith(PREFIX)) {
                return null;
            }

            token = token.replace(PREFIX, "");

            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);

            String usuario = claims.getBody().getSubject();
            String issuer = claims.getBody().getIssuer();
            Date expiration = claims.getBody().getExpiration();

            if (usuario != null && issuer.equals(EMISSOR) && expiration.after(new Date())) {
                return new UsernamePasswordAuthenticationToken(usuario, null, Collections.emptyList());
            }

            return null;

        } catch (Exception e) {
            return null;
        }
    }
}

