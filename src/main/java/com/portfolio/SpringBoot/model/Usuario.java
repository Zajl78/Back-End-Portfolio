
package com.portfolio.SpringBoot.model;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
    
    
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"username"}),
    @UniqueConstraint(columnNames = {"email"})})
public class Usuario {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "nombre", nullable = false)
    @NotEmpty(message = "El campo no puede estar vacio")
    private String nombre;

    @Column(name = "username", nullable = false)
    @NotEmpty(message = "El campo no puede estar vacio")
    private String username;

    @Column(name = "email", nullable = false)
    @NotEmpty(message = "El campo no puede estar vacio")
    @Email
    private String email;

    @Column(name = "password", nullable = false)
    @NotEmpty
    @Size(min = 8, message = "La contraseña debe ser de al menos 8 caracteres")
    private String password;

    
    
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "usuario_roles", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id"))
    private Set<Rol> roles = new HashSet<>();
}
    

