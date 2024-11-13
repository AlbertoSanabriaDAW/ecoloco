package com.example.ecoloco.servicios;

import com.example.ecoloco.modelos.Evento;
import com.example.ecoloco.repositorios.EventoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> findAllEventos() {
        return eventoRepository.findAll();
    }

    public Evento findEventosById(Integer id) {
        return eventoRepository.findById(id).orElse(null);
    }

    public Evento guardarEventos(Evento evento) {
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


}
