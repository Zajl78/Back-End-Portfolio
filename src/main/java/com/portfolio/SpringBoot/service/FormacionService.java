package com.portfolio.SpringBoot.service;

import com.portfolio.SpringBoot.model.Formacion;
import com.portfolio.SpringBoot.repository.FormacionRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormacionService implements IFormacionService {
    
    @Autowired
    public FormacionRepository formRepo;
    
    @Override
    public List<Formacion> getFormacion() {
     List<Formacion>ListaDeFormacion = formRepo.findAll();
     return ListaDeFormacion;
    }

    @Override
    public void saveFormacion(Formacion form) {
        
        formRepo.save(form);
    }

    @Override
    public void deleteFormacion(Long id) {
        
        formRepo.deleteById(id);
    }

    @Override
    public Formacion findFormacion(Long id) {
        
        Formacion form = formRepo.findById(id).orElse(null);
        
        return form;
    }
    
    public Formacion findFormacionByTitulo(String titulo) {
        
        return formRepo.findByTitulo(titulo).orElse(null);
             
    }
    
    public boolean existsFormacionByTitulo(String titulo) {
        
        return formRepo.existsByTitulo(titulo);
             
    }
    
    public boolean existsFormacionById(Long id) {
        
        return formRepo.existsById(id);
             
    }
    
}
