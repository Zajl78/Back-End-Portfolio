package com.portfolio.SpringBoot.service;

import com.portfolio.SpringBoot.model.Proyectos;
import java.util.List;

public interface IProyectosService {
    
    public List<Proyectos> getProyectos();

    public void saveProyectos(Proyectos proy);

    public void deleteProyectos(Long id);

    public Proyectos findProyectos(Long id);

   

}
