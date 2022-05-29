package com.portfolio.SpringBoot.service;

import com.portfolio.SpringBoot.model.Proyectos;
import com.portfolio.SpringBoot.repository.ProyectosRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProyectosService implements IProyectosService {
    
    @Autowired
    public ProyectosRepository proyRepo;
    
   
    @Override
    public List<Proyectos> getProyectos() {
     List<Proyectos>ListaDeProyectos = proyRepo.findAll();
     return ListaDeProyectos;
    }

    @Override
    public void saveProyectos(Proyectos proy) {
       
        proyRepo.save(proy);
    }

    @Override
    public void deleteProyectos(Long id) {
       
        proyRepo.deleteById(id);
    }

    @Override
    public Proyectos findProyectos(Long id) {
        
        Proyectos proy = proyRepo.findById(id).orElse(null);
        
        return proy;
    }
    
    public Proyectos findProyectosByProyecto(String proyecto) {
        
        return proyRepo.findByProyecto(proyecto).orElse(null);
             
    }
    
    public boolean existsProyectosByProyecto(String proyecto) {
        
        return proyRepo.existsByProyecto(proyecto);
             
    }
    
    public boolean existsProyectosById(Long id) {
        
        return proyRepo.existsById(id);
             
    }
    
}
