package com.example.ecoloco.servicios;

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
import java.util.List;

@Service
@AllArgsConstructor
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UsuarioEventoRepository usuarioEventoRepository;
    @Autowired
    private EventoMapper eventoMapper;

    public List<EventoDTO> listarEventosDTO() {
        List<Evento> eventos = eventoRepository.findAll();
        return eventoMapper.toDTO(eventos);
    }

    public Evento findEventosById(Integer id) {

        return eventoRepository.findById(id).orElse(null);
    }

    public Evento guardarEventos(EventoCrearDTO eventoCrearDTO) {
        Evento evento = new Evento();
        evento.setTitulo(eventoCrearDTO.getTitulo());
        evento.setUbicacion(eventoCrearDTO.getUbicacion());
        evento.setDescripcion(eventoCrearDTO.getDescripcion());

        //FECHA NACIMIENTO (STRING) -> LOCALDATE
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaFormateada = LocalDate.parse(eventoCrearDTO.getFecha(), formato);
        evento.setFecha(fechaFormateada);
        //Poner una imagen predeterminada y luego ya ver como se suben imagenes

        return eventoRepository.save(evento);
    }

    public String eliminarEventos(Integer id) {
        try {
            eventoRepository.deleteById(id);
            return "Evento eliminado";
        } catch (Exception e) {
            return "No se ha podido eliminar evento";
        }
    }

    //Metodo para eliminar usuario de evento (darse de baja)


    public void eliminarUsuarioDeEvento(Integer idUsuario, Integer idEvento) {
        usuarioEventoRepository.eliminarUsuarioDeEvento(idUsuario, idEvento);
    }

    public void eliminarUsuarioDeTodosLosEventos(Integer idUsuario) {
        usuarioEventoRepository.eliminarPorUsuarioId(idUsuario);
    }


}
