package com.example.ecoloco.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class EventoCrearDTO {
    private String titulo;
    private String fecha;
    private String ubicacion;
    private String descripcion;
    private String imagen;
}
