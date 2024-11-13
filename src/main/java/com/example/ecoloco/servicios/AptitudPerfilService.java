package com.example.ecoloco.servicios;

import com.example.ecoloco.modelos.AptitudPerfil;
import com.example.ecoloco.repositorios.AptitudPerfilRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AptitudPerfilService {

    @Autowired
    private AptitudPerfilRepository aptitudPerfilRepository;

    public List<AptitudPerfil> findAllAptitudPerfiles() {
        return aptitudPerfilRepository.findAll();
    }

    public AptitudPerfil findAptitudPerfilesById(Integer id) {
        return aptitudPerfilRepository.findById(id).orElse(null);
    }

    public AptitudPerfil guardarAptitudPerfiles(AptitudPerfil aptitudPerfil) {
        return aptitudPerfilRepository.save(aptitudPerfil);
    }

    public String eliminarAptitudPerfiles(Integer id) {
        try {
            aptitudPerfilRepository.deleteById(id);
            return "AptitudPerfil eliminado";
        } catch (Exception e) {
            return "No se ha podido eliminar aptitudPerfil";
        }
    }
}
