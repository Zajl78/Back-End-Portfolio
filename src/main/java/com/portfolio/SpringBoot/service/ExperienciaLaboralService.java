package com.portfolio.SpringBoot.service;

import com.portfolio.SpringBoot.model.ExperienciaLaboral;
import com.portfolio.SpringBoot.repository.ExperienciaLaboralRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ExperienciaLaboralService implements IExperienciaLaboralService {
    
    @Autowired
    public ExperienciaLaboralRepository expRepo;
    
    
    
     @Override
    public List<ExperienciaLaboral> getExperienciaLaboral() {
     List<ExperienciaLaboral>ListaDeExperienciaLaboral = expRepo.findAll();
     return ListaDeExperienciaLaboral;
    }

    @Override
    public void saveExperienciaLaboral(ExperienciaLaboral exp) {
        
        expRepo.save(exp);
    }

    @Override
    public void deleteExperienciaLaboral(Long id) {
        
        expRepo.deleteById(id);
    }

    @Override
    public ExperienciaLaboral findExperienciaLaboral(Long id) {
        
        ExperienciaLaboral exp = expRepo.findById(id).orElse(null);
        
        return exp;
    }
    
    public ExperienciaLaboral findExperienciaLaboralByPuesto(String puesto) {
        
        return expRepo.findByPuesto(puesto).orElse(null);
             
    }
    
    public boolean existsExperienciaLaboralByPuesto(String puesto) {
        
        return expRepo.existsByPuesto(puesto);
             
    }
    
    public boolean existsExperienciaLaboralById(Long id) {
        
        return expRepo.existsById(id);
             
    }
        
   
    }
    
    
    
    
