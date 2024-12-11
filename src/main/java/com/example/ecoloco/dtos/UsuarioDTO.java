package com.example.ecoloco.dtos;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Integer id;
    private String username;
    private String email;
    private String rol;
//    private PerfilDTO perfil;
}
