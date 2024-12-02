package com.example.ecoloco.servicios;

import com.example.ecoloco.dtos.PerfilDTO;
import com.example.ecoloco.dtos.PerfilEditarDTO;
import com.example.ecoloco.modelos.Perfil;
import com.example.ecoloco.repositorios.PerfilRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    public List<Perfil> findAllPerfiles() {
        return perfilRepository.findAll();
    }

    public Perfil findPerfilesById(Integer id) {
        return perfilRepository.findById(id).orElse(null);
    }

    public Perfil guardarPerfiles(Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    public String eliminarPerfiles(Integer id) {
        try {
            perfilRepository.deleteById(id);
            return "Perfil eliminado";
        } catch (Exception e) {
            return "No se ha podido eliminar el perfil";
        }
    }

    public Perfil editarPerfil(PerfilEditarDTO perfil, Integer id) {
        Perfil perfilActual = perfilRepository.findById(id).orElse(null);
        if (perfilActual == null) {
            return null;
        }
        perfilActual.setNombre(perfil.getNombre());
        perfilActual.setApellidos(perfil.getApellidos());
        perfilActual.setTelefono(perfil.getTelefono());
        perfilActual.setDni(perfil.getDni());
        perfilActual.setFechaNacimiento(perfil.getFechaNacimiento());
        return perfilRepository.save(perfilActual);
    }

}
