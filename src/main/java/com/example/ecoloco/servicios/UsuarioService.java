package com.example.ecoloco.servicios;

import com.example.ecoloco.modelos.Usuario;
import com.example.ecoloco.repositorios.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario findUsuariosById(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario guardarUsuarios(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public String eliminarUsuarios(Integer id) {
        try {
            usuarioRepository.deleteById(id);
            return "Usuario eliminado";
        } catch (Exception e) {
            return "No se ha podido eliminar usuario";
        }
    }

}
