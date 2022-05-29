package com.portfolio.SpringBoot.controller;

import com.portfolio.SpringBoot.dto.Mensaje;
import com.portfolio.SpringBoot.model.Formacion;
import com.portfolio.SpringBoot.service.FormacionService;
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

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping
public class FormacionController {

    @Autowired
    public FormacionService formServ;

    @GetMapping("/persona/formacion/traer")
    @ResponseBody
    public ResponseEntity<List<Formacion>> getFormacion() {
       
        List<Formacion>getFormacion = formServ.getFormacion();
        return new ResponseEntity(getFormacion, HttpStatus.OK);
    }
    
    @GetMapping("/persona/formacion/detalle/{id}")
    public ResponseEntity<Formacion> getById(@PathVariable (value = "id") Long id) {
        
        if(!formServ.existsFormacionById(id))
            
        return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        
        Formacion form = formServ.findFormacion(id);
    
        return new ResponseEntity(form, HttpStatus.OK);
    }
    
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/persona/formacion/crear")
    public ResponseEntity<?> createFormacion(@Valid @RequestBody Formacion form) {
        
         if(formServ.existsFormacionByTitulo(form.getTitulo()) )
            
        return new ResponseEntity(new Mensaje("Esa Formación ya existe"), HttpStatus.NOT_FOUND);
        

        formServ.saveFormacion(form);

        
        return new ResponseEntity(new Mensaje("formación creada"), HttpStatus.OK);

    }
    
//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/persona/formacion/borrar/{id}")
    public ResponseEntity<?> deleteFormacion(@PathVariable (value = "id") Long id) {

       
        formServ.deleteFormacion(id);

        return new ResponseEntity(new Mensaje("formación eliminada"), HttpStatus.OK);
    }
    
    
//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/persona/formacion/editar/{id}")
    public ResponseEntity<?> editFormacion(@PathVariable (value = "id") Long id, @Valid @RequestBody Formacion nuevaForm) {
        
         if(!formServ.existsFormacionById(id))
            
        return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        
        if(formServ.existsFormacionByTitulo(nuevaForm.getTitulo()) && formServ.findFormacionByTitulo(nuevaForm.getTitulo()).getId() !=id)
        return new ResponseEntity(new Mensaje ("esa Formación ya existe"), HttpStatus.BAD_REQUEST);
        
        
        Formacion form = formServ.findFormacion(id);

        form.setTipo(nuevaForm.getTipo());
        form.setInstitucion(nuevaForm.getInstitucion());
        form.setTitulo(nuevaForm.getTitulo());
        form.setLugar(nuevaForm.getLugar());
        form.setDesde(nuevaForm.getDesde());
        form.setHasta(nuevaForm.getHasta());
        form.setObservacion(nuevaForm.getObservacion());
        form.setLogo(nuevaForm.getLogo());
        
        
        formServ.saveFormacion(form);
        
        return new ResponseEntity(new Mensaje("formación actualizada"), HttpStatus.OK);
    }
    
    
}
