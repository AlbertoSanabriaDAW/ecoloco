package com.example.ecoloco.controladores;

import com.example.ecoloco.servicios.PerfilService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class PerfilController {

    private PerfilService perfilService;

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
