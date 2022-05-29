package com.portfolio.SpringBoot.service;

import com.portfolio.SpringBoot.model.Idiomas;
import com.portfolio.SpringBoot.repository.IdiomasRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdiomasService implements IIdiomasService {
    
    @Autowired
    public IdiomasRepository langRepo;
    
   
    @Override
    public List<Idiomas> getIdiomas() {
     List<Idiomas>ListaDeIdiomas = langRepo.findAll();
     return ListaDeIdiomas;
    }

    @Override
    public void saveIdiomas(Idiomas lang) {
       
        langRepo.save(lang);
    }

    @Override
    public void deleteIdiomas(Long id) {
      
        langRepo.deleteById(id);
    }

    @Override
    public Idiomas findIdiomas(Long id) {
        
        Idiomas lang = langRepo.findById(id).orElse(null);
        
        return lang;
    }
    
    public Idiomas findIdiomasByNombre(String nombre) {
        
        return langRepo.findByNombre(nombre).orElse(null);
             
    }
    
    public boolean existsIdiomasByNombre(String nombre) {
        
        return langRepo.existsByNombre(nombre);
             
    }
    
    public boolean existsIdiomasById(Long id) {
        
        return langRepo.existsById(id);
             
    }
    
}
