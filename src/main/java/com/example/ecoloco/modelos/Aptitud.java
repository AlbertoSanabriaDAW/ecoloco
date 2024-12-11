package com.example.ecoloco.modelos;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "aptitud", schema = "ecoloco", catalog = "postgres")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Aptitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;
}
