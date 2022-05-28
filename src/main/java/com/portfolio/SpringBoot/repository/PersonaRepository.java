package com.portfolio.SpringBoot.repository;

import com.portfolio.SpringBoot.model.Persona;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    Optional<Persona> findByFullName (String fullName);
    
    boolean existsByFullName (String fullName);
    

}
