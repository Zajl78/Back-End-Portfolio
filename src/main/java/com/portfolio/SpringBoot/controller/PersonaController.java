package com.portfolio.SpringBoot.controller;

import com.portfolio.SpringBoot.dto.Mensaje;
import com.portfolio.SpringBoot.model.Persona;
import com.portfolio.SpringBoot.service.IPersonaService;
import com.portfolio.SpringBoot.service.PersonaService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

@CrossOrigin(origins = "https://damp-island-31662.herokuapp.com/", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping
public class PersonaController {

    @Autowired
    public PersonaService persoServ;

    @GetMapping("/persona/traer")
    @ResponseBody
    public ResponseEntity<List<Persona>> getPersona() {
       
        List<Persona>getPersonas = persoServ.getPersonas();
        return new ResponseEntity(getPersonas, HttpStatus.OK);
    }
    
    @GetMapping("/persona/detalle/{id}")
    public ResponseEntity<Persona> getById(@PathVariable (value = "id") Long id) {
        
        if(!persoServ.existsPersonaById(id))
            
        return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        
        Persona per = persoServ.findPersona(id);
    
        return new ResponseEntity(per, HttpStatus.OK);
    }
    
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/persona/crear")
    public ResponseEntity<?> createPersona(@Valid @RequestBody Persona per) {
        
         if(persoServ.existsPersonaByFullName(per.getFullName()) )
            
        return new ResponseEntity(new Mensaje("Esa Persona ya existe"), HttpStatus.NOT_FOUND);
        


        persoServ.savePersona(per);
        
        return new ResponseEntity(new Mensaje("persona creada"), HttpStatus.OK);

    }
    
//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/persona/borrar/{id}")
    public ResponseEntity<?> deletePersona(@PathVariable (value = "id") Long id) {

        
        persoServ.deletePersona(id);

        return new ResponseEntity(new Mensaje("persona eliminada"), HttpStatus.OK);
    }
    
    
//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/persona/editar/{id}")
    public ResponseEntity<?> editPersona(@PathVariable (value = "id") Long id, @Valid @RequestBody Persona nuevaPer) {
    
         if(!persoServ.existsPersonaById(id))
            
        return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        
        if(persoServ.existsPersonaByFullName(nuevaPer.getFullName()) && persoServ.findPersonaByFullName(nuevaPer.getFullName()).getId() !=id)
        return new ResponseEntity(new Mensaje ("esa Persona ya existe"), HttpStatus.BAD_REQUEST);
        
        
        
        Persona per = persoServ.findPersona(id);

        per.setFullName(nuevaPer.getFullName());
        per.setPuesto(nuevaPer.getPuesto());
        per.setAcerca_de_mi(nuevaPer.getAcerca_de_mi());
        per.setFotoPerfil(nuevaPer.getFotoPerfil());
        
        
        
        persoServ.savePersona(per);
        
        return new ResponseEntity(new Mensaje("persona actualizada"), HttpStatus.OK);
    }
    
}