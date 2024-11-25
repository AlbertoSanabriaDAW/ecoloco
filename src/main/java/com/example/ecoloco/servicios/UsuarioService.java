package com.example.ecoloco.servicios;

import com.example.ecoloco.dtos.UsuarioDTO;
import com.example.ecoloco.dtos.UsuarioRegistroDTO;
import com.example.ecoloco.enumerados.Rol;
import com.example.ecoloco.mappers.UsuarioMapper;
import com.example.ecoloco.modelos.Usuario;
import com.example.ecoloco.repositorios.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private UsuarioMapper usuarioMapper;

    //Listar todos los usuarios con DTO
    public List<UsuarioDTO> listarUsuariosDTO() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarioMapper.toDTO(usuarios);
    }

    // Buscar usuario por id
    public Usuario findUsuariosById(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    // Registrar un usuario
    public Usuario registrarUsuarios(UsuarioRegistroDTO usuarioRegistroDTO) {
        //Verificar si el usuario ya existe
        if (usuarioRepository.existsByUsername(usuarioRegistroDTO.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }
        if (usuarioRepository.existsByEmail(usuarioRegistroDTO.getEmail())) {
            throw new RuntimeException("El correo ya está registrado");
        }

        //Crear usuario
        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioRegistroDTO.getUsername());
        usuario.setPassword(usuarioRegistroDTO.getPassword()); //Encriptar
        usuario.setEmail(usuarioRegistroDTO.getEmail());
        usuario.setRol(Rol.VOLUNTARIO); //Rol por defecto

        //Guardar usuario
        return usuarioRepository.save(usuario);
    }


    // Autenticar el usuario (login)
    public Usuario autenticarUsuario(String username, String password) {
        Optional<Usuario> usuario = usuarioRepository.findByUsername(username);
        if (usuario.isPresent() && usuario.get().getPassword().equals(password)) {
            return usuario.get();
        } else {
            throw new RuntimeException("Usuario o contraseña incorrectos");
        }
    }

    // Eliminar un usuario
    public String eliminarUsuario(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        // Eliminar las relaciones del usuario con los eventos
        eventoService.eliminarUsuarioDeTodosLosEventos(id);
        // Eliminar el usuario
        usuarioRepository.deleteById(id);
        return "Usuario eliminado";


    }

}
