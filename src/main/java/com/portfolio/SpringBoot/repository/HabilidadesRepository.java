package com.portfolio.SpringBoot.repository;

import com.portfolio.SpringBoot.model.Habilidades;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface HabilidadesRepository extends JpaRepository<Habilidades, Long> {
    
    Optional<Habilidades> findByHabilidad (String habilidad);
    
    boolean existsByHabilidad (String habilidad);
}
