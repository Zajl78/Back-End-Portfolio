package com.portfolio.SpringBoot.controller;

import com.portfolio.SpringBoot.dto.Mensaje;
import com.portfolio.SpringBoot.model.Proyectos;
import com.portfolio.SpringBoot.service.ProyectosService;
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

@CrossOrigin(origins = "*http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping
public class ProyectosController {

    @Autowired
    public ProyectosService proyServ;

    @GetMapping("/persona/proyectos/traer")
    @ResponseBody
    public ResponseEntity<List<Proyectos>> getProyectos () {
       
        List<Proyectos>getProyectos = proyServ.getProyectos();
        return new ResponseEntity(getProyectos, HttpStatus.OK);
    }
    
    @GetMapping("/persona/proyectos/detalle/{id}")
    public ResponseEntity<Proyectos> getById(@PathVariable (value = "id") Long id) {
        
        if(!proyServ.existsProyectosById(id))
            
        return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        
        Proyectos form = proyServ.findProyectos(id);
    
        return new ResponseEntity(form, HttpStatus.OK);
    }
    
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/persona/proyectos/crear")
    public ResponseEntity<?> createProyectos(@Valid @RequestBody Proyectos proy) {
        
         if(proyServ.existsProyectosByProyecto(proy.getProyecto()) )
            
        return new ResponseEntity(new Mensaje("Ese Proyecto ya existe"), HttpStatus.NOT_FOUND);
        


        proyServ.saveProyectos(proy);
        
        return new ResponseEntity(new Mensaje("proyecto creado"), HttpStatus.OK);

    }
    
//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/persona/proyectos/borrar/{id}")
    public ResponseEntity<?> deleteProyectos(@PathVariable (value = "id") Long id) {

        
        proyServ.deleteProyectos(id);

        return new ResponseEntity(new Mensaje("proyecto eliminado"), HttpStatus.OK);
    }
    
    
//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/persona/proyectos/editar/{id}")
    public ResponseEntity<?> editProyectos(@PathVariable (value = "id") Long id, @Valid @RequestBody Proyectos nuevoProy) {
    
         if(!proyServ.existsProyectosById(id))
            
        return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        
        if(proyServ.existsProyectosByProyecto(nuevoProy.getProyecto()) && proyServ.findProyectosByProyecto(nuevoProy.getProyecto()).getId() !=id)
        return new ResponseEntity(new Mensaje ("ese Proyecto ya existe"), HttpStatus.BAD_REQUEST);
        
        
        
        Proyectos proy = proyServ.findProyectos(id);

        proy.setProyecto(nuevoProy.getProyecto());
        proy.setFecha(nuevoProy.getFecha());
        proy.setDescripcion(nuevoProy.getDescripcion());
        proy.setImagen(nuevoProy.getImagen());
        proy.setLink(nuevoProy.getLink());
        
        
        
        proyServ.saveProyectos(proy);
        
        return new ResponseEntity(new Mensaje("proyecto actualizado"), HttpStatus.OK);
    }
}
