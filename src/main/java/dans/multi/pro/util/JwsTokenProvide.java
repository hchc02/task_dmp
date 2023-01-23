package dans.multi.pro.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwsTokenProvide {
    @Value("${jwt.secret}")
	private String secret;
  
    @Value("${jwt.expired}")
    private int expiredtime;
  
    public String generateJwtToken(String username) {
  
      return Jwts.builder().setSubject((username)).setIssuedAt(new Date())
          .setExpiration(new Date((new Date()).getTime() + expiredtime)).signWith(SignatureAlgorithm.HS512, secret)
          .compact();
    }
  
    public String getUserNameFromJwtToken(String token) {
      return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
  
    public boolean validateJwtToken(String authToken) {
      try {
        Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
        return true;
      } catch (SignatureException e) {
        log.error("Invalid JWT signature: {}", e.getMessage());
      } catch (MalformedJwtException e) {
        log.error("Invalid JWT token: {}", e.getMessage());
      } catch (ExpiredJwtException e) {
        log.error("JWT token is expired: {}", e.getMessage());
      } catch (UnsupportedJwtException e) {
        log.error("JWT token is unsupported: {}", e.getMessage());
      } catch (IllegalArgumentException e) {
        log.error("JWT claims string is empty: {}", e.getMessage());
      }
  
      return false;
    }
}
