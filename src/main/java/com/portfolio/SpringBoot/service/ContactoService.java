package com.portfolio.SpringBoot.service;

import com.portfolio.SpringBoot.model.Contacto;
import com.portfolio.SpringBoot.repository.ContactoRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ContactoService implements IContactoService {

    @Autowired
    public ContactoRepository contactRepo;

    
    @Override
    public List<Contacto> getContacto() {
     List<Contacto>ListaDeContactos = contactRepo.findAll();
     return ListaDeContactos;
    }

    @Override
    public void saveContacto(Contacto cont) {
        
        
        contactRepo.save(cont);
    }

    @Override
    public void deleteContacto(Long id) {
        
        contactRepo.deleteById(id);
    }

    @Override
    public Contacto findContacto(Long id) {
        
        Contacto contacto = contactRepo.findById(id).orElse(null);
        
        return contacto;
        
    }
    
    
    public Contacto findContactoByEmail(String email) {
        
        return contactRepo.findByEmail(email).orElse(null);
             
    }
    
    public boolean existsContactoByEmail(String email) {
        
        return contactRepo.existsByEmail(email);
             
    }
    
    public boolean existsContactoById(Long id) {
        
        return contactRepo.existsById(id);
             
    }
    
    

}
