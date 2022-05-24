package com.portfolio.SpringBoot.service;

import com.portfolio.SpringBoot.model.ExperienciaLaboral;
import java.util.List;

public interface IExperienciaLaboralService {

    public List<ExperienciaLaboral> getExperienciaLaboral();

    public void saveExperienciaLaboral(ExperienciaLaboral exp);

    public void deleteExperienciaLaboral(Long id);

    public ExperienciaLaboral findExperienciaLaboral(Long id);

   

}
