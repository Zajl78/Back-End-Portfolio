
package com.portfolio.SpringBoot.excepciones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class portfolioException extends RuntimeException{
    
    private static final long serialVersionUID=1L;
    private HttpStatus estado;
    private String mensaje;
    

    public portfolioException(HttpStatus estado, String mensaje, String mensaje1) {
        this.estado = estado;
        this.mensaje = mensaje;
        this.mensaje= mensaje1;
    }
    
    
    
}
