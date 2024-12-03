package com.example.ecoloco.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PerfilEditarDTO {
    private String nombre;
    private String apellidos;
    private String telefono;
    private String dni;
    private LocalDate fechaNacimiento;
}
