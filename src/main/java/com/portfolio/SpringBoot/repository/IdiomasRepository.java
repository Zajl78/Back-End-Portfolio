package com.portfolio.SpringBoot.repository;

import com.portfolio.SpringBoot.model.Idiomas;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface IdiomasRepository extends JpaRepository<Idiomas, Long> {
    
    Optional<Idiomas> findByNombre (String nombre);
    
    boolean existsByNombre (String nombre);
    
}
