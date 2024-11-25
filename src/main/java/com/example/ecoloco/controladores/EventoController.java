package com.example.ecoloco.controladores;

import com.example.ecoloco.dtos.EventoCrearDTO;
import com.example.ecoloco.dtos.EventoDTO;
import com.example.ecoloco.mappers.EventoMapper;
import com.example.ecoloco.modelos.Evento;
import com.example.ecoloco.servicios.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;
    private final EventoMapper eventoMapper;

    public EventoController(EventoMapper eventoMapper) {
        this.eventoMapper = eventoMapper;
    }

    @GetMapping("/lista")
    public List<EventoDTO> obtenerEventos() {
        return eventoService.listarEventosDTO();
    }

    @GetMapping()
    public EventoDTO obtenerEvento(@RequestParam Integer id){
        Evento evento = eventoService.findEventosById(id);
        return eventoMapper.toDTO(evento);
    }

    @PostMapping("/crear")
    public Evento guardarEvento(@RequestBody EventoCrearDTO eventoCrearDTO){
        return eventoService.guardarEventos(eventoCrearDTO);
    }

    @DeleteMapping("/eliminar")
    public String deleteEventoById(@RequestParam Integer id){
        return eventoService.eliminarEventos(id);
    }

}
