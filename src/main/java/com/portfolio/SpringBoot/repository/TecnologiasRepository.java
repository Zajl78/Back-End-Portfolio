package com.portfolio.SpringBoot.repository;

import com.portfolio.SpringBoot.model.Tecnologias;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface TecnologiasRepository extends JpaRepository<Tecnologias, Long> {
    
    Optional<Tecnologias> findByTecnologia (String tecnologia);
    
    boolean existsByTecnologia (String tecnologia);
    
}
