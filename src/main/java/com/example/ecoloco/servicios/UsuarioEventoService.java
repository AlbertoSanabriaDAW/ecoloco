package com.example.ecoloco.servicios;

import com.example.ecoloco.dtos.ErroresDTO;
import com.example.ecoloco.modelos.Evento;
import com.example.ecoloco.modelos.Usuario;
import com.example.ecoloco.modelos.UsuarioEvento;
import com.example.ecoloco.repositorios.EventoRepository;
import com.example.ecoloco.repositorios.UsuarioEventoRepository;
import com.example.ecoloco.repositorios.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioEventoService {

    @Autowired
    private UsuarioEventoRepository usuarioEventoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EventoRepository eventoRepository;

    public ErroresDTO agregarUsuarioAEvento(Integer idEvento, Integer idUsuario) {
        // Verificar si el evento existe
        Evento evento = eventoRepository.findByIdAndDeletedFalse(idEvento)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

        // Verificar si el usuario existe
        Usuario usuario = usuarioRepository.findByIdAndDeletedFalse(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        ErroresDTO erroresDTO = new ErroresDTO();
        // Crear la relación UsuarioEvento
        UsuarioEvento usuarioEvento = new UsuarioEvento();
        usuarioEvento.setEvento(evento);
        usuarioEvento.setUsuario(usuario);
        if (usuarioEventoRepository.findByEventoIdAndUsuarioId(idEvento, idUsuario).isPresent()) {
            erroresDTO.setMensaje("El usuario ya está inscrito en el evento");
            return erroresDTO;
        }
        usuarioEventoRepository.save(usuarioEvento);

        usuario.getEventosParticipados().add(usuarioEvento);
        usuarioRepository.save(usuario);
        evento.getUsuariosParticipantes().add(usuarioEvento);
        eventoRepository.save(evento);

        erroresDTO.setMensaje("Usuario inscrito correctamente");
        return erroresDTO;
    }

    public void eliminarUsuarioDeEvento(Integer idEvento, Integer idUsuario) {
        // Buscar la relación entre el usuario y el evento
        Optional<UsuarioEvento> usuarioEventoOptional = usuarioEventoRepository.findByEventoIdAndUsuarioId(idEvento, idUsuario);

        if (usuarioEventoOptional.isPresent()) {
            // Eliminar la relación
            UsuarioEvento usuarioEvento = usuarioEventoOptional.get();
            usuarioEventoRepository.delete(usuarioEvento);
        } else {
            // Si no se encuentra la relación, lanzar una excepción
            throw new RuntimeException("Usuario no encontrado en el evento");
        }
    }

    // Métodos para darse de alta y darse de baja
    public ErroresDTO inscripcion(Integer idEvento, Integer idUsuario) {
        return agregarUsuarioAEvento(idEvento, idUsuario);
    }

    public void anularInscripcion(Integer idEvento, Integer idUsuario) {
        eliminarUsuarioDeEvento(idEvento, idUsuario);
    }

    public void eliminarUsuarioDeTodosLosEventos(Integer idUsuario) {
        List<UsuarioEvento> usuarioEventos = usuarioEventoRepository.findByUsuarioId(idUsuario);
        for (UsuarioEvento usuarioEvento : usuarioEventos) {
            eliminarUsuarioDeEvento(usuarioEvento.getEvento().getId(), idUsuario);
        }
    }
}
