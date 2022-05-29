package com.portfolio.SpringBoot.service;

import com.portfolio.SpringBoot.model.Tecnologias;
import com.portfolio.SpringBoot.repository.TecnologiasRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TecnologiasService implements ITecnologiasService {
    
    @Autowired
    public TecnologiasRepository tecRepo;
    
    
    @Override
    public List<Tecnologias> getTecnologias() {
     List<Tecnologias>ListaDeTecnologias = tecRepo.findAll();
     return ListaDeTecnologias;
    }

    @Override
    public void saveTecnologias(Tecnologias tec) {
        
        tecRepo.save(tec);
    }

    @Override
    public void deleteTecnologias(Long id) {
        
        tecRepo.deleteById(id);
    }

    @Override
    public Tecnologias findTecnologias(Long id) {
        
        Tecnologias tec = tecRepo.findById(id).orElse(null);
        
        return tec;
    }
    
    public Tecnologias findTecnologiasByTecnologia(String tecnologia) {
        
        return tecRepo.findByTecnologia(tecnologia).orElse(null);
             
    }
    
    public boolean existsTecnologiasByTecnologia(String tecnologia) {
        
        return tecRepo.existsByTecnologia(tecnologia);
             
    }
    
    public boolean existsTecnologiasById(Long id) {
        
        return tecRepo.existsById(id);
             
    }
    
}
