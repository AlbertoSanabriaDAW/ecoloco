package com.example.ecoloco.controladores;

import com.example.ecoloco.dtos.PerfilEditarDTO;
import com.example.ecoloco.modelos.Perfil;
import com.example.ecoloco.servicios.PerfilService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

    private PerfilService perfilService;

    @PostMapping("editar/{id}")
    public Perfil editarPerfil(@PathVariable Integer id, @RequestBody PerfilEditarDTO perfil) {
        return perfilService.editarPerfil(perfil, id);
    }

    // Consultar perfil
//    @GetMapping("/usuarios/{id}/perfil")
//    public Perfil obtenerPerfil(@PathVariable Integer id) {
//        return perfilService.findPerfilesById(id);
//    }

    // Actualizar perfil
//    @PutMapping("/usuarios/{id}/perfil")
//    public String actualizarPerfil(Integer id) {
//        return "Perfil actualizado";
//    }
}
