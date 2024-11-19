package com.example.ecoloco.controladores;

import com.example.ecoloco.dtos.UsuarioDTO;
import com.example.ecoloco.modelos.Usuario;
import com.example.ecoloco.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Listar todos los usuarios
    @GetMapping("/lista")
    public List<UsuarioDTO> obtenerUsuarios() {
        return usuarioService.listarUsuariosDTO();
    }

    // Consultar un usuario
    @GetMapping("/{id}")
    public Usuario obtenerUsuario(@PathVariable Integer id) {
        return usuarioService.findUsuariosById(id);
    }

    // Registrar un usuario
    @PostMapping("/registro")
    public Usuario guardarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioService.convertirAEntidad(usuarioDTO);
        return usuarioService.registrarUsuarios(usuario);
    }

    // Autenticar un usuario
    @PostMapping("/login")
    public Usuario loginUsuario(Usuario usuario) {
        return usuarioService.findUsuariosById(usuario.getId());
    }

    // Eliminar un usuario
    @DeleteMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Integer id) {
        return usuarioService.eliminarUsuarios(id);
    }

}
