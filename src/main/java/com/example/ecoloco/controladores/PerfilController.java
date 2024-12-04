package com.example.ecoloco.controladores;

import com.example.ecoloco.dtos.PerfilEditarDTO;
import com.example.ecoloco.modelos.Perfil;
import com.example.ecoloco.servicios.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @PostMapping("editar/{id}")
    public Perfil editarPerfil(@PathVariable Integer id, @RequestBody PerfilEditarDTO perfil) {
        return perfilService.editarPerfil(perfil, id);
    }

    @GetMapping("/{id}")
    public Perfil obtenerPerfil(@PathVariable Integer id) {
        return perfilService.findPerfilById(id);
    }

    // Actualizar perfil
//    @PutMapping("/usuarios/{id}/perfil")
//    public String actualizarPerfil(Integer id) {
//        return "Perfil actualizado";
//    }
}
