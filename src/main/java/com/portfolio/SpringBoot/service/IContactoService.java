package com.portfolio.SpringBoot.service;

import com.portfolio.SpringBoot.model.Contacto;
import java.util.List;

public interface IContactoService {

    
    public List<Contacto> getContacto();

    public void saveContacto(Contacto cont);

    public void deleteContacto(Long id);

    public Contacto findContacto(Long id);
    
    

}
