package com.example.ecoloco.servicios;

import com.example.ecoloco.dtos.PerfilDTO;
import com.example.ecoloco.dtos.UsuarioDTO;
import com.example.ecoloco.enumerados.Rol;
import com.example.ecoloco.modelos.Usuario;
import com.example.ecoloco.repositorios.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Listar todos los usuarios con DTO
    public List<UsuarioDTO> listarUsuariosDTO() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDTO> usuarioDTOs = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            usuarioDTOs.add(convertirADTO(usuario));
        }
        return usuarioDTOs;
    }

    // Métodos de conversión a DTO
    public UsuarioDTO convertirADTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setUsername(usuario.getUsername());
        dto.setEmail(usuario.getEmail());
        dto.setRol(usuario.getRol().name());

        if (usuario.getPerfil() != null) {
            PerfilDTO perfilDTO = new PerfilDTO();
            perfilDTO.setId(usuario.getPerfil().getId());
            perfilDTO.setNombre(usuario.getPerfil().getNombre());
            perfilDTO.setApellidos(usuario.getPerfil().getApellidos());
            perfilDTO.setTelefono(usuario.getPerfil().getTelefono());
            dto.setPerfil(perfilDTO);
        }

        return dto;
    }

    // Metodo de conversión a entidad
    public Usuario convertirAEntidad(UsuarioDTO usuarioDTO) {
        if (usuarioDTO == null) {
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setEmail(usuarioDTO.getEmail());
        try {
            usuario.setRol(Rol.valueOf(usuarioDTO.getRol()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Rol no válido: " + usuarioDTO.getRol());
        }
        return usuario;
    }

    // Buscar usuario por id
    public Usuario findUsuariosById(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    // Registrar un usuario
    public Usuario registrarUsuarios(Usuario usuario) {
        if (usuarioRepository.existsByUsername(usuario.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("El correo ya está registrado");
        }
        //Guardar usuario
        return usuarioRepository.save(usuario);
    }

    // Autenticar el usuario (login)
    public Usuario autenticarUsuario(String username, String password) {
        Optional<Usuario> usuario = usuarioRepository.findByUsername(username);
        if (usuario.isPresent() && usuario.get().getPassword().equals(password)) {
            return usuario.get();
        }else {
            throw new RuntimeException("Usuario o contraseña incorrectos");
        }
    }

    // Eliminar un usuario
    public String eliminarUsuarios(Integer id) {
        try {
            usuarioRepository.deleteById(id);
            return "Usuario eliminado";
        } catch (Exception e) {
            return "No se ha podido eliminar usuario";
        }
    }

}
