package com.example.ecoloco.servicios;

import com.example.ecoloco.enumerados.Rol;
import com.example.ecoloco.modelos.Usuario;
import com.example.ecoloco.repositorios.UsuarioRepository;
import com.example.ecoloco.security.AuthenticationRequest;
import com.example.ecoloco.security.AuthenticationResponse;
import com.example.ecoloco.security.JWTService;
import com.example.ecoloco.security.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var usuario = Usuario.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(Rol.VOLUNTARIO)
                .build();
        repository.save(usuario);
        var token = jwtService.generarToken(usuario);
        return AuthenticationResponse.builder().token(token).build();
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var usuario = repository.findByUsernameAndDeletedFalse(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generarToken(usuario);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    //Este servicio no está en uso ya que se ha implementado un servicio de usuario
}
