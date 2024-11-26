    package com.example.ecoloco.servicios;

    import com.example.ecoloco.dtos.UsuarioDTO;
    import com.example.ecoloco.dtos.UsuarioRegistroDTO;
    import com.example.ecoloco.enumerados.Rol;
    import com.example.ecoloco.mappers.UsuarioMapper;
    import com.example.ecoloco.modelos.Usuario;
    import com.example.ecoloco.modelos.UsuarioEvento;
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
        private UsuarioEventoService usuarioEventoService;

        @Autowired
        private UsuarioMapper usuarioMapper;

        //Listar todos los usuarios con DTO
        public List<UsuarioDTO> listarUsuariosDTO() {
            List<Usuario> usuarios = usuarioRepository.findAllByDeletedFalse();
            return usuarioMapper.toDTO(usuarios);
        }

        // Buscar usuario por id
        public Usuario findUsuariosById(Integer id) {
            return usuarioRepository.findByIdAndDeletedFalse(id).orElse(null);
        }

        // Registrar un usuario
        public Usuario registrarUsuarios(UsuarioRegistroDTO usuarioRegistroDTO) {
            //Verificar si el usuario ya existe
            if (usuarioRepository.existsByUsernameAndDeletedFalse(usuarioRegistroDTO.getUsername())) {
                throw new RuntimeException("El nombre de usuario ya existe");
            }
            if (usuarioRepository.existsByEmailAndDeletedFalse(usuarioRegistroDTO.getEmail())) {
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
            Optional<Usuario> usuario = usuarioRepository.findByUsernameAndDeletedFalse(username);
            if (usuario.isPresent() && usuario.get().getPassword().equals(password)) {
                return usuario.get();
            } else {
                throw new RuntimeException("Usuario o contraseña incorrectos");
            }
        }

        // Eliminar un usuario
        public String eliminarUsuario(Integer id) {
            // Verificar si el usuario existe
            if (!usuarioRepository.existsById(id)) {
                throw new RuntimeException("Usuario no encontrado");
            }
            // Eliminar (marcar como eliminado) las relaciones del usuario con los eventos
            usuarioEventoService.eliminarUsuarioDeTodosLosEventos(id);
            // Marcar el usuario como eliminado
            Usuario usuario = usuarioRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            usuario.setDeleted(true);
            usuarioRepository.save(usuario);

            return "Usuario eliminado";


        }


    }
