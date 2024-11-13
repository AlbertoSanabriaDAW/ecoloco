package com.example.ecoloco.servicios;

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

}
