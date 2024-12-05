package com.example.ecoloco.controladores;

import com.example.ecoloco.dtos.ErroresDTO;
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

    @Autowired
    private EventoMapper eventoMapper;


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

    // Darse de alta al evento
    @PostMapping("/darseDeAlta/{idEvento}/{idUsuario}")
    public ErroresDTO darseDeAlta(@PathVariable Integer idEvento, @PathVariable Integer idUsuario){
        return eventoService.darseDeAlta(idEvento, idUsuario);
    }

    // Darse de baja al evento
    @PostMapping("/desapuntarse/{idEvento}/{idUsuario}")
    public ErroresDTO darseDeBaja(@PathVariable Integer idEvento, @PathVariable Integer idUsuario){
        eventoService.darseDeBaja(idEvento, idUsuario);
        return new ErroresDTO("Se ha dado de baja correctamente");
    }

    // Listar eventos por usuario
    @GetMapping("/usuario/{idUsuario}")
    public List<EventoDTO> listarEventosPorUsuario(@PathVariable Integer idUsuario) {
        return eventoService.listarEventosPorUsuario(idUsuario);
    }

}
