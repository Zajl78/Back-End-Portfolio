package com.portfolio.SpringBoot.repository;

import com.portfolio.SpringBoot.model.Contacto;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ContactoRepository extends JpaRepository<Contacto, Long> {
    
    Optional<Contacto> findByEmail (String email);
    
    
    boolean existsByEmail (String email);
    
    
    
}
