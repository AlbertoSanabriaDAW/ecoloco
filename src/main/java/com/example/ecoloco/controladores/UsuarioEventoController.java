package com.example.ecoloco.controladores;

import com.example.ecoloco.dtos.EventoDTO;
import com.example.ecoloco.modelos.UsuarioEvento;
import com.example.ecoloco.servicios.UsuarioEventoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/inscripcion")
public class UsuarioEventoController {

    private UsuarioEventoService usuarioEventoService;

    @PostMapping("/unirse")
    public EventoDTO unirse(@RequestBody UsuarioEvento dto){
        return usuarioEventoService.unirse(dto);
    }
}
