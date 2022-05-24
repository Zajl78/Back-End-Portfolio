
package com.portfolio.SpringBoot.repository;

import com.portfolio.SpringBoot.model.Rol;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{
   
    public Optional<Rol>findByNombre(String nombre);
   
}
