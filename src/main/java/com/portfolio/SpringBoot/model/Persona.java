package com.portfolio.SpringBoot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "persona", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"fullName"})})
public class Persona {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id

    private Long id;

    @Column(name = "fullName", nullable = false)
    private String fullName;
    @Column(name = "puesto", nullable = false)
    private String puesto;
    @Column(name = "acerca_de_mi", nullable = false)
    @Size(max=500)
    private String acerca_de_mi;
    private String fotoPerfil;

}
