package com.example.ecoloco.modelos;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "aptitud_evento", schema = "ecoloco", catalog = "postgres")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AptitudEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "id_aptitud")
    private Aptitud aptitud;
}
