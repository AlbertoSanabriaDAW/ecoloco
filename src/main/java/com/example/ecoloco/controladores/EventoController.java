package com.example.ecoloco.controladores;

import com.example.ecoloco.servicios.EventoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class EventoController {

    private EventoService eventoService;

    // Listar todos los eventos
//    @GetMapping("/eventos")
//    public List<Evento> obtenerEventos() {
//        return eventoService.findAllEventos();
//    }

    // Consultar un evento
//    @GetMapping("/eventos/{id}")
//    public Evento obtenerEvento(Integer id) {
//        return eventoService.findEventosById(id);
//    }

    // Crear un evento (admin)
//    @PostMapping("/eventos")
//    public Evento crearEvento(Evento evento) {
//        return eventoService.guardarEventos(evento);
//    }

    // Editar un evento (admin)
//    @PutMapping("/eventos/{id}")
//    public String editarEvento(Integer id) {
//        return "Evento editado";
//    }

    // Eliminar un evento (admin)
//    @DeleteMapping("/eventos/{id}")
//    public String eliminarEvento(Integer id) {
//        return eventoService.eliminarEventos(id);
//    }

}
