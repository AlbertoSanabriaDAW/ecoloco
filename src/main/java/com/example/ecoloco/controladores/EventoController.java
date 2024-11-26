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

    // Listar todos los eventos✅
    @GetMapping("/lista")
    public List<EventoDTO> obtenerEventos() {
        return eventoService.listarEventosDTO();
    }

    // Consultar un evento✅
    @GetMapping()
    public EventoDTO obtenerEvento(@RequestParam Integer id){
        Evento evento = eventoService.findEventosById(id);
        return eventoMapper.toDTO(evento);
    }

    // Crear un evento (ADMIN)✅
    @PostMapping("/crear")
    public Evento guardarEvento(@RequestBody EventoCrearDTO eventoCrearDTO){
        return eventoService.guardarEventos(eventoCrearDTO);
    }

    // Eliminar un evento (ADMIN)✅
    @DeleteMapping("/eliminar")
    public String deleteEventoById(@RequestParam Integer id){
        return eventoService.eliminarEventos(id);
    }

}
