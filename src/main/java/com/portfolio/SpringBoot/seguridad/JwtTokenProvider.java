
package com.portfolio.SpringBoot.seguridad;

import com.portfolio.SpringBoot.excepciones.portfolioException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
    
    @Value("${app.jwt-secret}")
    private String jwtSecret;
    
    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;
    
//    obtiene el token
    
    public String generarToken(Authentication authentication) {
    String username = authentication.getName();
    Date fechaActual = new Date();
    Date fechaExpiracion = new Date(fechaActual.getTime() + jwtExpirationInMs);
    
    String token = Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(fechaExpiracion).signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    
    return token;
    }
    
//    obtiene el usuario asociado al token
    
     public String obtenerUsernameDelJWT(String token){
    Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    
    return claims.getSubject();
    }
    
     
//    valida el token
            
    public boolean validarToken(String token){
    
    try{
        
        Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
        return true;
    
    }
     catch(SignatureException ex){
    
        throw new portfolioException(HttpStatus.BAD_REQUEST, "Firma JWT no válida");
    }
    
    catch(MalformedJwtException ex){
    
        throw new portfolioException(HttpStatus.BAD_REQUEST, "Token JWT no válido");
    }
    
    catch(ExpiredJwtException ex){
    
        throw new portfolioException(HttpStatus.BAD_REQUEST, "Token JWT caducado");
    }
    
    catch(UnsupportedJwtException ex){
    
        throw new portfolioException(HttpStatus.BAD_REQUEST, "Token JWT no compatible");
    }
    
    catch(IllegalArgumentException ex){
    
        throw new portfolioException(HttpStatus.BAD_REQUEST, "La cadena claims JWT está vacía");
    }
    
    }
    
   
    
}
