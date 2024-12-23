package com.example.ecoloco.servicios;

import com.example.ecoloco.dtos.ErroresDTO;
import com.example.ecoloco.dtos.EventoCrearDTO;
import com.example.ecoloco.dtos.EventoDTO;
import com.example.ecoloco.mappers.EventoMapper;
import com.example.ecoloco.modelos.Evento;
import com.example.ecoloco.modelos.UsuarioEvento;
import com.example.ecoloco.repositorios.EventoRepository;
import com.example.ecoloco.repositorios.UsuarioEventoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private EventoMapper eventoMapper;
    @Autowired
    private UsuarioEventoService usuarioEventoService;
    @Autowired
    private UsuarioEventoRepository usuarioEventoRepository;

    //Metodo para listar eventos
    public List<EventoDTO> listarEventosDTO() {
        List<Evento> eventos = eventoRepository.findAllByDeletedFalse();
        return eventoMapper.toDTO(eventos);
    }

    //Metodo para buscar evento por id
    public Evento findEventosById(Integer id) {

        return eventoRepository.findByIdAndDeletedFalse(id).orElse(null);
    }

    //Metodo para guardar evento
    public Evento guardarEventos(EventoCrearDTO eventoCrearDTO) {
        Evento evento = new Evento();
        evento.setTitulo(eventoCrearDTO.getTitulo());
        evento.setUbicacion(eventoCrearDTO.getUbicacion());
        evento.setDescripcion(eventoCrearDTO.getDescripcion());

        //FECHA DEL EVENTO (STRING) -> LOCALDATE
        if (eventoCrearDTO.getFecha() == null || eventoCrearDTO.getFecha().isEmpty() || eventoCrearDTO.getFecha().isBlank() || eventoCrearDTO.getFecha().equals("")) {
            eventoCrearDTO.setFecha(LocalDate.now().toString());
        }
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaFormateada = LocalDate.parse(eventoCrearDTO.getFecha(), formato);
        evento.setFecha(fechaFormateada);
        //Poner una imagen predeterminada y luego ya ver como se suben imagenes
        evento.setImagen(eventoCrearDTO.getImagen());
        evento.setDeleted(false);

        return eventoRepository.save(evento);
    }

    //Metodo para eliminar evento
    public String eliminarEventos(Integer id) {
        Optional<Evento> eventoOptional = eventoRepository.findById(id);
        if (eventoOptional.isPresent()) {
            Evento evento = eventoOptional.get();
            evento.setDeleted(true);
            eventoRepository.save(evento);
            return "Evento eliminado";
        } else {
            throw new RuntimeException("Evento no encontrado");
        }
    }

    //Metodo para agregar usuario a evento (darse de alta)
    public ErroresDTO darseDeAlta(Integer idEvento, Integer idUsuario) {
        return usuarioEventoService.inscripcion(idEvento, idUsuario);
    }

    //Metodo para eliminar usuario de evento (darse de baja)
    public void darseDeBaja(Integer idEvento, Integer idUsuario) {
        usuarioEventoService.anularInscripcion(idEvento, idUsuario);
    }


    public List<EventoDTO> listarEventosPorUsuario(Integer idUsuario) {
        List<UsuarioEvento> eventoList = usuarioEventoRepository.findByUsuarioId(idUsuario);
        List<Evento> eventos = new ArrayList<>();
        for (UsuarioEvento usuarioEvento : eventoList) {
            eventos.add(usuarioEvento.getEvento());
        }
        return eventoMapper.toDTO(eventos);
    }
}
