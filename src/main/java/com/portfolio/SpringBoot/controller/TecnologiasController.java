package com.portfolio.SpringBoot.controller;

import com.portfolio.SpringBoot.dto.Mensaje;
import com.portfolio.SpringBoot.model.Tecnologias;
import com.portfolio.SpringBoot.service.ITecnologiasService;
import com.portfolio.SpringBoot.service.TecnologiasService;
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

@CrossOrigin
@RestController
@RequestMapping ("/api/persona/tecnologias")
public class TecnologiasController {

    @Autowired
    public TecnologiasService tecServ;

    @GetMapping("/traer")
    @ResponseBody
    public ResponseEntity<List<Tecnologias>> getTecnologias() {
       
        List<Tecnologias>getTecnologias = tecServ.getTecnologias();
        return new ResponseEntity(getTecnologias, HttpStatus.OK);
    }
    
    @GetMapping("/detalle/{id}")
    public ResponseEntity<Tecnologias> getById(@PathVariable (value = "id") Long id) {
        
        if(!tecServ.existsTecnologiasById(id))
            
        return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        
        Tecnologias tec = tecServ.findTecnologias(id);
    
        return new ResponseEntity(tec, HttpStatus.OK);
    }
    
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crear")
    public ResponseEntity<?> createTecnologias(@Valid @RequestBody Tecnologias tec) {

         if(tecServ.existsTecnologiasByTecnologia(tec.getTecnologia()) )
            
        return new ResponseEntity(new Mensaje("Esa Tecnolog??a ya existe"), HttpStatus.NOT_FOUND);
        

        tecServ.saveTecnologias(tec);
        
        return new ResponseEntity(new Mensaje("tecnolog??a creada"), HttpStatus.OK);

    }
    
//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> deleteTecnologias(@PathVariable (value = "id") Long id) {

        
        tecServ.deleteTecnologias(id);

        return new ResponseEntity(new Mensaje("tecnolog??a eliminada"), HttpStatus.OK);
    }
    
    
//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editTecnologias(@PathVariable (value = "id") Long id, @Valid @RequestBody Tecnologias nuevaTec) {
    
         if(!tecServ.existsTecnologiasById(id))
            
        return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        
        if(tecServ.existsTecnologiasByTecnologia(nuevaTec.getTecnologia()) && tecServ.findTecnologiasByTecnologia(nuevaTec.getTecnologia()).getId() !=id)
        return new ResponseEntity(new Mensaje ("esa Tecnolog??a ya existe"), HttpStatus.BAD_REQUEST);
        
        
        
        Tecnologias tec = tecServ.findTecnologias(id);

        tec.setTecnologia(nuevaTec.getTecnologia());
        tec.setPorcentaje(nuevaTec.getPorcentaje());
        tec.setLogo(nuevaTec.getLogo());
        
        
        tecServ.saveTecnologias(tec);
        
        return new ResponseEntity(new Mensaje("tecnolog??a actualizada"), HttpStatus.OK);
    }

}
