
package com.portfolio.SpringBoot.dto;

import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistroDTO {
    private String nombre;
    private String username;
    private String email;
    private String password;
    private Set<String> roles = new HashSet<>();
}
