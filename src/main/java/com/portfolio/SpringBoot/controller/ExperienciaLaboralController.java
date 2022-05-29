package com.portfolio.SpringBoot.controller;

import com.portfolio.SpringBoot.dto.Mensaje;
import com.portfolio.SpringBoot.model.ExperienciaLaboral;
import com.portfolio.SpringBoot.service.ExperienciaLaboralService;
import com.portfolio.SpringBoot.service.IExperienciaLaboralService;
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

@CrossOrigin(origins = "https://frontend-portfolio-zulyma-j.web.app", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping
public class ExperienciaLaboralController {

    @Autowired
    public ExperienciaLaboralService expServ;
    
    
    
    
    @GetMapping("/persona/experienciaLaboral/traer")
    @ResponseBody
    public ResponseEntity<List<ExperienciaLaboral>> getExperienciaLaboral() {
       
        List<ExperienciaLaboral>getExperienciaLaboral = expServ.getExperienciaLaboral();
        return new ResponseEntity(getExperienciaLaboral, HttpStatus.OK);
    }
    
    
    @GetMapping("/persona/experienciaLaboral/detalle/{id}")
    public ResponseEntity<ExperienciaLaboral> getById(@PathVariable (value = "id") Long id) {
        
        if(!expServ.existsExperienciaLaboralById(id))
            
        return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        
        ExperienciaLaboral exp = expServ.findExperienciaLaboral(id);
    
        return new ResponseEntity(exp, HttpStatus.OK);
    }
    
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/persona/experienciaLaboral/crear")
    public ResponseEntity<?> createExperienciaLaboral(@Valid @RequestBody ExperienciaLaboral exp) {
        
        if(expServ.existsExperienciaLaboralByPuesto(exp.getPuesto()) )
            
        return new ResponseEntity(new Mensaje("Esa experiencia ya existe"), HttpStatus.NOT_FOUND);

        expServ.saveExperienciaLaboral(exp);

        
        return new ResponseEntity(new Mensaje("experiencia laboral creada"), HttpStatus.OK);

    }
    
//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/persona/experienciaLaboral/borrar/{id}")
    public ResponseEntity<?> deleteExperienciaLaboral(@PathVariable (value = "id") Long id) {

        
        expServ.deleteExperienciaLaboral(id);

        return new ResponseEntity(new Mensaje("experiencia laboral eliminada"), HttpStatus.OK);
    }
    
    
//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/persona/experienciaLaboral/editar/{id}")
    public ResponseEntity<?> editExperienciaLaboral(@PathVariable (value = "id") Long id, @Valid @RequestBody ExperienciaLaboral nuevaExp) {
        
       if(!expServ.existsExperienciaLaboralById(id))
            
        return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        
        if(expServ.existsExperienciaLaboralByPuesto(nuevaExp.getPuesto()) && expServ.findExperienciaLaboralByPuesto(nuevaExp.getPuesto()).getId() !=id)
        return new ResponseEntity(new Mensaje ("esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
        
        ExperienciaLaboral exp = expServ.findExperienciaLaboral(id);

        exp.setPuesto(nuevaExp.getPuesto());
        exp.setEmpresa(nuevaExp.getEmpresa());
        exp.setPais(nuevaExp.getPais());
        exp.setDesde(nuevaExp.getDesde());
        exp.setHasta(nuevaExp.getHasta());
        exp.setDescripcion(nuevaExp.getDescripcion());
        exp.setLogo(nuevaExp.getLogo());
        
  
        
        expServ.saveExperienciaLaboral(exp);
        
        return new ResponseEntity(new Mensaje("experiencia  laboral actualizada"), HttpStatus.OK);
    }

   

}
