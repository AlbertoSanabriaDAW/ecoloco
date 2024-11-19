package com.example.ecoloco.controladores;

import com.example.ecoloco.dtos.EventoDTO;
import com.example.ecoloco.modelos.UsuarioEvento;
import com.example.ecoloco.servicios.UsuarioEventoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/inscripcion")
public class UsuarioEventoController {

    private UsuarioEventoService usuarioEventoService;

    // Inscribirse a un evento
//    @PostMapping("/eventos/{id}/inscripcion")
//    public EventoDTO unirse(@RequestBody UsuarioEvento dto){
//        return usuarioEventoService.unirse(dto);
//    }

    // Cancelar inscripción a un evento
//    @DeleteMapping("/eventos/{id}/inscripcion/{id_usuario}")
//    public String cancelarInscripcion(@PathVariable Integer id, @PathVariable Integer id_usuario){
//        return usuarioEventoService.cancelarInscripcion(id, id_usuario);
//    }

    //Listar todos los eventos a los que se ha inscrito un usuario
//    @GetMapping("/usuarios/{id}/eventos")
//    public List<EventoDTO> obtenerEventosInscritos(@PathVariable Integer id){
//        return usuarioEventoService.obtenerEventosInscritos(id);
//    }

    //Listar todos los usuarios inscritos a un evento
//    @GetMapping("/eventos/{id}/usuarios")
//    public List<UsuarioEvento> obtenerUsuariosInscritos(@PathVariable Integer id){
//        return usuarioEventoService.obtenerUsuariosInscritos(id);
//    }

}
