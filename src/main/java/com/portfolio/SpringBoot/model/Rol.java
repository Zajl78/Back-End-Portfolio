
package com.portfolio.SpringBoot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Entity
@NoArgsConstructor
@Table (name="roles")
public class Rol {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
   
    @Column(name = "nombre", nullable = false)
    @NotEmpty(message = "El campo no puede estar vacio")
    private String nombre;
}
