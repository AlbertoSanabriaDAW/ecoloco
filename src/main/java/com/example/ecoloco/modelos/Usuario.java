package com.example.ecoloco.modelos;

import com.example.ecoloco.enumerados.Rol;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "usuario", schema = "ecoloco", catalog = "postgres")
@Getter
@Setter
@ToString (exclude = {"perfil"})
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "rol")
    @Enumerated(EnumType.ORDINAL)
    private Rol rol;

    @Column(name = "deleted")
    private boolean deleted = false;

    // Relación uno a uno con Perfil
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Perfil perfil;

    // Relación muchos a muchos con Evento (usuario participa en múltiples eventos)
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<UsuarioEvento> eventosParticipados;


}
