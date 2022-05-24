package com.portfolio.SpringBoot.service;

import com.portfolio.SpringBoot.model.Idiomas;
import java.util.List;

public interface IIdiomasService {

    public List<Idiomas> getIdiomas();

    public void saveIdiomas(Idiomas lang);

    public void deleteIdiomas(Long id);

    public Idiomas findIdiomas(Long id);

   

}
