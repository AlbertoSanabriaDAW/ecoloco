    package com.example.ecoloco.servicios;

    import com.example.ecoloco.dtos.UsuarioDTO;
    import com.example.ecoloco.enumerados.Rol;
    import com.example.ecoloco.mappers.UsuarioMapper;
    import com.example.ecoloco.modelos.Usuario;
    import com.example.ecoloco.repositorios.UsuarioRepository;
    import com.example.ecoloco.security.AuthenticationRequest;
    import com.example.ecoloco.security.AuthenticationResponse;
    import com.example.ecoloco.security.JWTService;
    import com.example.ecoloco.security.RegisterRequest;
    import lombok.AllArgsConstructor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;

    import java.util.HashMap;
    import java.util.List;

    @Service
    @AllArgsConstructor
    public class UsuarioService {

        @Autowired
        private UsuarioRepository usuarioRepository;
        @Autowired
        private UsuarioEventoService usuarioEventoService;
        @Autowired
        private UsuarioMapper usuarioMapper;
        private final PasswordEncoder passwordEncoder;
        private final JWTService jwtService;
        private final AuthenticationManager authenticationManager;


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
        public AuthenticationResponse registrarUsuario(RegisterRequest request) {
            if (usuarioRepository.existsByUsernameAndDeletedFalse(request.getUsername())) {
                throw new RuntimeException("El nombre de usuario ya existe");
            }
            if (usuarioRepository.existsByEmailAndDeletedFalse(request.getEmail())) {
                throw new RuntimeException("El correo ya está registrado");
            }

            Usuario usuario = Usuario.builder()
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .rol(Rol.ADMIN) // Rol por defecto
                    .build();

            usuarioRepository.save(usuario);

            String token = jwtService.generarToken(usuario);
            return AuthenticationResponse.builder()
                    .token(token)
                    .build();
        }



        // Autenticar el usuario (login)
        public AuthenticationResponse autenticarUsuario(AuthenticationRequest request) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            Usuario usuario = usuarioRepository.findByUsernameAndDeletedFalse(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            // Generar token pasado el userId
            String token = jwtService.generarToken(new HashMap<>(), usuario);

            return AuthenticationResponse.builder()
                    .token(token)
                    .userId(usuario.getId())
                    .build();
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
