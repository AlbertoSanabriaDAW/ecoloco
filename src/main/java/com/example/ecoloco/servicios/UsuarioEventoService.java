package com.example.ecoloco.servicios;

import com.example.ecoloco.dtos.EventoDTO;
import com.example.ecoloco.modelos.Evento;
import com.example.ecoloco.modelos.Perfil;
import com.example.ecoloco.modelos.Usuario;
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
    private EventoService eventoService;
    private UsuarioService usuarioService;


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

//    public EventoDTO unirse(UsuarioEvento dto) {
//        UsuarioEvento entity = new UsuarioEvento();
//        Usuario usuario = usuarioService.findUsuariosById(dto.getUsuario().getId());
//        Evento evento = eventoService.findEventosById(dto.getEvento().getId());
//
//        if (usuario != null && evento != null) {
//            entity.setUsuario(usuario);
//            entity.setEvento(evento);
//            guardarUsuarioEventos(entity);
//            return new EventoDTO(evento);
//        }else{
//         UsuarioEvento unirse = usuarioEventoRepository.findBy(dto.getUsuario().getId(), dto.getEvento().getId()).orElse(null);
//         return getInscripcion(unirse);
//        }
//    }

}
