
package com.portfolio.SpringBoot.controller;

import com.portfolio.SpringBoot.dto.LoginDTO;
import com.portfolio.SpringBoot.dto.RegistroDTO;
import com.portfolio.SpringBoot.model.Rol;
import com.portfolio.SpringBoot.model.Usuario;
import com.portfolio.SpringBoot.repository.RolRepository;
import com.portfolio.SpringBoot.repository.UsuarioRepository;
import com.portfolio.SpringBoot.seguridad.JWTAuthResponseDTO;
import com.portfolio.SpringBoot.seguridad.JwtTokenProvider;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persona/auth")
@CrossOrigin
public class AuthController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
     @Autowired
    private RolRepository rolRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @PostMapping("/iniciarSesion")
    public ResponseEntity<JWTAuthResponseDTO> authenticateUser(@RequestBody LoginDTO loginDTO){
        
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));
   
     SecurityContextHolder.getContext().setAuthentication(authentication);
     
      //obtenemos el token del jwtTokenProvider
    String token = jwtTokenProvider.generarToken(authentication);
    UserDetails userDetails = (UserDetails)authentication.getPrincipal();
    
    return ResponseEntity.ok(new JWTAuthResponseDTO(token, userDetails.getUsername(), userDetails.getAuthorities()));
    
    
//    return new ResponseEntity<>("Ha iniciado sesión con éxito!", HttpStatus.OK);
    }
    
    
       @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroDTO registroDTO){
    if(usuarioRepository.existsByUsername(registroDTO.getUsername())){
        
        return new ResponseEntity<>("Ese nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
    }
    
    if(usuarioRepository.existsByEmail(registroDTO.getEmail())){
        
        return new ResponseEntity<>("Ese email de usuario ya existe", HttpStatus.BAD_REQUEST);
    }
  
    Usuario usuario = new Usuario();
    usuario.setNombre(registroDTO.getNombre());
    usuario.setUsername(registroDTO.getUsername());
    usuario.setEmail(registroDTO.getEmail());
    usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));
    
    Rol roles = rolRepository.findByNombre("ROLE_ADMIN").get();
    usuario.setRoles(Collections.singleton(roles));
    
    usuarioRepository.save(usuario);
    return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.OK);
}
}
