package com.example.ecoloco.mappers;

import com.example.ecoloco.dtos.UsuarioDTO;
import com.example.ecoloco.modelos.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    /**
     * Metodo para convertir un UsuarioDTO a Usuario
     * @param usuarioDTO
     * @return
     */

    Usuario toEntity(UsuarioDTO usuarioDTO);

    /**
     * Metodo para convertir un Usuario a UsuarioDTO
     * @param usuario
     * @return
     */

    UsuarioDTO toDTO(Usuario usuario);

    /**
     * Metodo para convertir una lista de UsuarioDTO a Usuario
     */

    List<Usuario> toEntity(List<UsuarioDTO> dtos);

    /**
     * Metodo para convertir una lista de Usuario a UsuarioDTO
     */

    List<UsuarioDTO> toDTO(List<Usuario> entities);
}
