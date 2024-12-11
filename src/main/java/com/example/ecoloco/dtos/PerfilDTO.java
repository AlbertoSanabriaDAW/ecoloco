package com.example.ecoloco.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PerfilDTO {
    private Integer id;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String dni;
    private LocalDate fechaNacimiento;
}
