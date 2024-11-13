package com.example.ecoloco.servicios;

import com.example.ecoloco.modelos.UsuarioEvento;
import com.example.ecoloco.repositorios.UsuarioEventoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioEventoService {

    @Autowired
    private UsuarioEventoRepository usuarioEventoRepository;

    public List<UsuarioEvento> findAllUsuarioEventos() {
        return usuarioEventoRepository.findAll();
    }

    public UsuarioEvento findUsuarioEventosById(Integer id) {
        return usuarioEventoRepository.findById(id).orElse(null);
    }

    public UsuarioEvento guardarUsuarioEventos(UsuarioEvento usuarioEvento) {
        return usuarioEventoRepository.save(usuarioEvento);
    }

    public String eliminarUsuarioEventos(Integer id) {
        try {
            usuarioEventoRepository.deleteById(id);
            return "UsuarioEvento eliminado";
        } catch (Exception e) {
            return "No se ha podido eliminar usuarioEvento";
        }
    }

}
