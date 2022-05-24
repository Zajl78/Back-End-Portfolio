package com.portfolio.SpringBoot.service;

import com.portfolio.SpringBoot.model.Persona;
import com.portfolio.SpringBoot.repository.PersonaRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PersonaService implements IPersonaService {

    @Autowired
    public PersonaRepository persoRepo;
    
    
     @Override
    public List<Persona> getPersonas() {
     List<Persona>ListaDePersonas = persoRepo.findAll();
     return ListaDePersonas;
    }

    @Override
    public void savePersona(Persona per) {
        
        
        persoRepo.save(per);
    }

    @Override
    public void deletePersona(Long id) {
        
        persoRepo.deleteById(id);
        
   
    }

    @Override
    public Persona findPersona(Long id) {
        
        Persona per = persoRepo.findById(id).orElse(null);
        
        return per;
    }
    
    
    public Persona findPersonaByFullName(String fullName) {
        
        return persoRepo.findByFullName(fullName).orElse(null);
             
    }
    
    public boolean existsPersonaByFullName(String fullName) {
        
        return persoRepo.existsByFullName(fullName);
             
    }
    
    public boolean existsPersonaById(Long id) {
        
        return persoRepo.existsById(id);
             
    }
}
    