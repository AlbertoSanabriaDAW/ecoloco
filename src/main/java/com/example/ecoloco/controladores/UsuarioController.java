package com.example.ecoloco.controladores;

import com.example.ecoloco.dtos.UsuarioDTO;
import com.example.ecoloco.dtos.UsuarioLoginDTO;
import com.example.ecoloco.dtos.UsuarioRegistroDTO;
import com.example.ecoloco.mappers.UsuarioMapper;
import com.example.ecoloco.modelos.Usuario;
import com.example.ecoloco.security.AuthenticationRequest;
import com.example.ecoloco.security.AuthenticationResponse;
import com.example.ecoloco.security.RegisterRequest;
import com.example.ecoloco.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioMapper usuarioMapper;


    // Listar todos los usuarios
    @GetMapping("/lista")
    public List<UsuarioDTO> obtenerUsuarios() {
        return usuarioService.listarUsuariosDTO();
    }

    // Consultar un usuario
    @GetMapping()
    public UsuarioDTO obtenerUsuario(@RequestParam Integer id) {
        Usuario usuario = usuarioService.findUsuariosById(id);
        return usuarioMapper.toDTO(usuario);
    }

    // Registrar un usuario
    @PostMapping("/registro")
    public ResponseEntity<AuthenticationResponse> guardarUsuario(@RequestBody RegisterRequest request) {
        AuthenticationResponse response = usuarioService.registrarUsuario(request);
        return ResponseEntity.ok(response);
    }

    // Autenticar un usuario
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginUsuario(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = usuarioService.autenticarUsuario(request);
        return ResponseEntity.ok(response);
    }


    // Eliminar un usuario
    @DeleteMapping("/eliminar")
    public String deleteUsuarioById(@RequestParam Integer id) {
        return usuarioService.eliminarUsuario(id);
    }

}
