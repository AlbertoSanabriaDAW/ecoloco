package com.example.ecoloco.modelos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "perfil", schema = "ecoloco", catalog = "postgres")
@Getter
@Setter
@ToString (exclude = {"usuario"})
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "dni")
    private String dni;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    @JsonBackReference
    private Usuario usuario;


}
