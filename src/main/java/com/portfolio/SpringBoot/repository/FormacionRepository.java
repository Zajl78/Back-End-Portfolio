package com.portfolio.SpringBoot.repository;

import com.portfolio.SpringBoot.model.Formacion;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface FormacionRepository extends JpaRepository<Formacion, Long> {
    
    Optional<Formacion> findByTitulo (String titulo);
    
    boolean existsByTitulo (String titulo);
    
}
