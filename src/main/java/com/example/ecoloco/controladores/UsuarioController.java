package com.example.ecoloco.controladores;

import com.example.ecoloco.modelos.Usuario;
import com.example.ecoloco.servicios.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    @GetMapping("/lista")
    public List<Usuario> obtenerUsuarios() {
        return usuarioService.findAllUsuarios();
    }

    @PostMapping("/registro")
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioService.guardarUsuarios(usuario);
    }

    @PostMapping("/login")
    public Usuario loginUsuario(Usuario usuario) {
        return usuarioService.findUsuariosById(usuario.getId());
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Integer id) {
        return usuarioService.eliminarUsuarios(id);
    }

}
