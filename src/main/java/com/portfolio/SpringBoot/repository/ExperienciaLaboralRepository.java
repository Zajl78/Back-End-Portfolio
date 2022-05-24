package com.portfolio.SpringBoot.repository;

import com.portfolio.SpringBoot.model.ExperienciaLaboral;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ExperienciaLaboralRepository extends JpaRepository<ExperienciaLaboral, Long> {
   
    Optional<ExperienciaLaboral> findByPuesto (String puesto);
    
    boolean existsByPuesto (String puesto);

}
