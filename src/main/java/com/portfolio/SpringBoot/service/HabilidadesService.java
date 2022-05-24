package com.portfolio.SpringBoot.service;

import com.portfolio.SpringBoot.model.Habilidades;
import com.portfolio.SpringBoot.repository.HabilidadesRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class HabilidadesService implements IHabilidadesService {
    
    @Autowired
    public HabilidadesRepository habRepo;
    
    
    @Override
    public List<Habilidades> getHabilidades() {
     List<Habilidades>ListaDeHabilidades = habRepo.findAll();
     return ListaDeHabilidades;
    }

    @Override
    public void saveHabilidades(Habilidades hab) {
       
        habRepo.save(hab);
    }

    @Override
    public void deleteHabilidades(Long id) {
        
        habRepo.deleteById(id);
    }

    @Override
    public Habilidades findHabilidades(Long id) {
        
        Habilidades hab = habRepo.findById(id).orElse(null);
        
        return hab;
    }
    
   public Habilidades findHabilidadesByHabilidad(String habilidad) {
        
        return habRepo.findByHabilidad(habilidad).orElse(null);
             
    }
    
    public boolean existsHabilidadesByHabilidad(String habilidad) {
        
        return habRepo.existsByHabilidad(habilidad);
             
    }
    
    public boolean existsHabilidadesById(Long id) {
        
        return habRepo.existsById(id);
             
    }
    
}
