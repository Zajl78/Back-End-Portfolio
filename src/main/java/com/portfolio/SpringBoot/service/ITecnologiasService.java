package com.portfolio.SpringBoot.service;

import com.portfolio.SpringBoot.model.Tecnologias;
import java.util.List;

public interface ITecnologiasService {
    
    public List<Tecnologias> getTecnologias();

    public void saveTecnologias(Tecnologias tec);

    public void deleteTecnologias(Long id);

    public Tecnologias findTecnologias(Long id);
    
    

}
