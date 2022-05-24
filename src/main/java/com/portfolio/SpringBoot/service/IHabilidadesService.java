package com.portfolio.SpringBoot.service;

import com.portfolio.SpringBoot.model.Habilidades;
import java.util.List;

public interface IHabilidadesService {
    
    public List<Habilidades> getHabilidades();

    public void saveHabilidades(Habilidades hab);

    public void deleteHabilidades(Long id);

    public Habilidades findHabilidades(Long id);

   
}
