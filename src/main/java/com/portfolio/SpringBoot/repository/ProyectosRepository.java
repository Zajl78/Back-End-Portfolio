package com.portfolio.SpringBoot.repository;

import com.portfolio.SpringBoot.model.Proyectos;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ProyectosRepository extends JpaRepository<Proyectos, Long> {
    
    Optional<Proyectos> findByProyecto (String proyecto);
    
    boolean existsByProyecto (String proyecto);
    
}
