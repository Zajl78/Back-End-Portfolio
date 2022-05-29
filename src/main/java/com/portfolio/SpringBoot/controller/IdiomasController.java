package com.portfolio.SpringBoot.controller;

import com.portfolio.SpringBoot.dto.Mensaje;
import com.portfolio.SpringBoot.model.Idiomas;
import com.portfolio.SpringBoot.service.IIdiomasService;
import com.portfolio.SpringBoot.service.IdiomasService;
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
public class IdiomasController {

    @Autowired
    public IdiomasService langServ;
    
    @GetMapping("/persona/idiomas/traer")
    @ResponseBody
    public ResponseEntity<List<Idiomas>> getIdiomasPorPersonaId() {
       
        List<Idiomas>getIdiomas = langServ.getIdiomas();
        return new ResponseEntity(getIdiomas, HttpStatus.OK);
    }
    
    @GetMapping("/persona/idiomas/detalle/{id}")
    public ResponseEntity<Idiomas> getById(@PathVariable (value = "id") Long id) {
        
        if(!langServ.existsIdiomasById(id))
            
        return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        
        Idiomas lang = langServ.findIdiomas(id);
    
        return new ResponseEntity(lang, HttpStatus.OK);
    }
    
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/persona/idiomas/crear")
    public ResponseEntity<?> createIdiomas(@Valid @RequestBody Idiomas lang) {
        
         if(langServ.existsIdiomasByNombre(lang.getNombre()) )
            
        return new ResponseEntity(new Mensaje("Ese Idioma ya existe"), HttpStatus.NOT_FOUND);
        

        langServ.saveIdiomas(lang);

        
        return new ResponseEntity(new Mensaje("idioma creado"), HttpStatus.OK);

    }
    
//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/persona/idiomas/borrar/{id}")
    public ResponseEntity<?> deleteIdiomas(@PathVariable (value = "id") Long id) {

        
        langServ.deleteIdiomas(id);

        return new ResponseEntity(new Mensaje("idioma eliminado"), HttpStatus.OK);
    }
    
    
//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/persona/idiomas/editar/{id}")
    public ResponseEntity<?> editIdiomas(@PathVariable (value = "id") Long id, @Valid @RequestBody Idiomas nuevoLang) {
        
        if(!langServ.existsIdiomasById(id))
            
        return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        
        if(langServ.existsIdiomasByNombre(nuevoLang.getNombre()) && langServ.findIdiomasByNombre(nuevoLang.getNombre()).getId() !=id)
        return new ResponseEntity(new Mensaje ("esa Formación ya existe"), HttpStatus.BAD_REQUEST);
        
        
        
        Idiomas lang = langServ.findIdiomas(id);

        lang.setNombre(nuevoLang.getNombre());
        lang.setNivel(nuevoLang.getNivel());
        
        
        langServ.saveIdiomas(lang);
        
        return new ResponseEntity(new Mensaje("idioma actualizado"), HttpStatus.OK);
    }
    
    
    
    
}
