package com.example.ecoloco.dtos;

import lombok.Data;

@Data
public class EventoDTO {

    private Integer id;
    private String titulo;
    private String fecha;
    private String ubicacion;
    private String descripcion;

}
