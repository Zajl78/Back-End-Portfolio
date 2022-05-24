package com.portfolio.SpringBoot.controller;

import com.portfolio.SpringBoot.dto.Mensaje;
import com.portfolio.SpringBoot.model.Habilidades;
import com.portfolio.SpringBoot.service.HabilidadesService;
import com.portfolio.SpringBoot.service.IHabilidadesService;
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

@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping
public class HabilidadesController {

    @Autowired
    public HabilidadesService habServ;

    @GetMapping("/persona/habilidades/traer")
    @ResponseBody
    public ResponseEntity<List<Habilidades>> getHabilidades() {
       
        List<Habilidades>getHabilidades = habServ.getHabilidades();
        return new ResponseEntity(getHabilidades, HttpStatus.OK);
    }
    
    @GetMapping("/persona/habilidades/detalle/{id}")
    public ResponseEntity<Habilidades> getById(@PathVariable (value = "id") Long id) {
        
        if(!habServ.existsHabilidadesById(id))
            
        return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        
        Habilidades hab = habServ.findHabilidades(id);
    
        return new ResponseEntity(hab, HttpStatus.OK);
    }
    
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/persona/habilidades/crear")
    public ResponseEntity<?> createHabilidades(@Valid @RequestBody Habilidades hab) {
        
        if(habServ.existsHabilidadesByHabilidad(hab.getHabilidad()) )
            
        return new ResponseEntity(new Mensaje("Esa Habilidad ya existe"), HttpStatus.NOT_FOUND);
        

        habServ.saveHabilidades(hab);
        
        return new ResponseEntity(new Mensaje("habilidad creada"), HttpStatus.OK);

    }
    
//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/persona/habilidades/borrar/{id}")
    public ResponseEntity<?> deleteHabilidades(@PathVariable (value = "id") Long id) {

        
        habServ.deleteHabilidades(id);

        return new ResponseEntity(new Mensaje("habilidad eliminada"), HttpStatus.OK);
    }
    
    
//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/persona/habilidades/editar/{id}")
    public ResponseEntity<?> editHabilidades(@PathVariable (value = "id") Long id, @Valid @RequestBody Habilidades nuevaHab) {
        
         if(!habServ.existsHabilidadesById(id))
            
        return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        
        if(habServ.existsHabilidadesByHabilidad(nuevaHab.getHabilidad()) && habServ.findHabilidadesByHabilidad(nuevaHab.getHabilidad()).getId() !=id)
        return new ResponseEntity(new Mensaje ("esa Habilidad ya existe"), HttpStatus.BAD_REQUEST);
        
        
        Habilidades hab = habServ.findHabilidades(id);

        hab.setHabilidad(nuevaHab.getHabilidad());        
        
        
        habServ.saveHabilidades(hab);
        
        return new ResponseEntity(new Mensaje("habilidad actualizada"), HttpStatus.OK);
    }
}
