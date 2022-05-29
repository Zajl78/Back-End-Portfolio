package com.portfolio.SpringBoot.controller;

import com.portfolio.SpringBoot.dto.Mensaje;
import com.portfolio.SpringBoot.model.Contacto;
import com.portfolio.SpringBoot.repository.ContactoRepository;
import com.portfolio.SpringBoot.service.ContactoService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "https://frontend-portfolio-zulyma-j.web.app", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping
public class ContactoController {

    @Autowired
    public ContactoService contactServ;
    @Autowired
    public ContactoRepository contactRepo;
    
    @GetMapping("/persona/contacto/traer")
    @ResponseBody
    public ResponseEntity<List<Contacto>> getContacto() {
       
        List<Contacto>getContacto = contactServ.getContacto();
        return new ResponseEntity(getContacto, HttpStatus.OK);
    }
    
    @GetMapping("/persona/contacto/detalle/{id}")
    public ResponseEntity<Contacto> getById(@PathVariable (value = "id") Long id) {
        
        if(!contactServ.existsContactoById(id))
            
        return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        
        Contacto cont = contactServ.findContacto(id);
    
        return new ResponseEntity(cont, HttpStatus.OK);
    }
    
    
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/persona/contacto/crear")
    public ResponseEntity<?> createContacto(@Valid @RequestBody Contacto cont) {
        
//        if(StringUtils.isBlank(cont.getDireccion(), cont.getTelefono(), cont.getEmail()))
//            
//        return new ResponseEntity(new Mensaje("Los campos son obligatorios"), HttpStatus.BAD_REQUEST);

        if(contactServ.existsContactoByEmail(cont.getEmail()) )
            
        return new ResponseEntity(new Mensaje("Ese contacto ya existe"), HttpStatus.NOT_FOUND);
       
        contactServ.saveContacto(cont);
        return new ResponseEntity(new Mensaje("contacto creado"), HttpStatus.OK);

    }
    
//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/persona/contacto/borrar/{id}")
    public ResponseEntity<?> deleteContacto(@PathVariable (value = "id") Long id) {

        
        contactServ.deleteContacto(id);

        return new ResponseEntity(new Mensaje("contacto eliminado"), HttpStatus.OK);
    }
    
    
//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/persona/contacto/editar/{id}")
    public ResponseEntity<?> editContacto(@PathVariable (value = "id") Long id, @Valid @RequestBody Contacto nuevoCont) {
        
        
        if(!contactServ.existsContactoById(id))
            
        return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        
        if(contactServ.existsContactoByEmail(nuevoCont.getEmail()) && contactServ.findContactoByEmail(nuevoCont.getEmail()).getId() !=id)
        return new ResponseEntity(new Mensaje ("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        
        Contacto cont = contactServ.findContacto(id);

        cont.setEmail (nuevoCont.getEmail());
        cont.setDireccion(nuevoCont.getDireccion());
        cont.setTelefono(nuevoCont.getTelefono());
       
        
        contactServ.saveContacto(cont);
        
        return new ResponseEntity(new Mensaje("contacto actualizado"), HttpStatus.OK);
    }
    
}