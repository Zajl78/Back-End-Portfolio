package com.portfolio.SpringBoot.service;

import com.portfolio.SpringBoot.model.Formacion;
import java.util.List;

public interface IFormacionService {

    public List<Formacion> getFormacion();

    public void saveFormacion(Formacion form);

    public void deleteFormacion(Long id);

    public Formacion findFormacion(Long id);

}
