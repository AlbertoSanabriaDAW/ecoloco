package com.example.ecoloco.modelos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "evento", schema = "ecoloco", catalog = "postgres")
@Getter
@Setter
@ToString(exclude = "usuariosParticipantes")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "ubicacion")
    private String ubicacion;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "imagen")
    private String imagen;

    @Column(name = "deleted")
    private boolean deleted = false;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<UsuarioEvento> usuariosParticipantes;

}
