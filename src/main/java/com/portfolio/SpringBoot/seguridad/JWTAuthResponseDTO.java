
package com.portfolio.SpringBoot.seguridad;

import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JWTAuthResponseDTO {
    
    private String tokenDeAcceso;
    private String tipoDeToken = "Bearer";
    private String username;
    private Collection<? extends GrantedAuthority> authorities;

    public JWTAuthResponseDTO(String tokenDeAcceso, String username, Collection<? extends GrantedAuthority> authorities) {
        this.tokenDeAcceso = tokenDeAcceso;
        this.username = username;
        this.authorities = authorities;
    }
    

   
    
}
