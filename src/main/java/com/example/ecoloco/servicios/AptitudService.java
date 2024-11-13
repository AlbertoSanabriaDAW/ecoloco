package com.example.ecoloco.servicios;

import com.example.ecoloco.modelos.Aptitud;
import com.example.ecoloco.repositorios.AptitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AptitudService {

    @Autowired
    private AptitudRepository aptitudRepository;

    public List<Aptitud> findAllAptitudes() {
        return aptitudRepository.findAll();
    }

    public Aptitud findAptitudesById(Integer id) {
        return aptitudRepository.findById(id).orElse(null);
    }

    public Aptitud guardarAptitudes(Aptitud aptitud) {
        return aptitudRepository.save(aptitud);
    }

    public String eliminarAptitudes(Integer id) {
        try {
            aptitudRepository.deleteById(id);
            return "Aptitud eliminado";
        } catch (Exception e) {
            return "No se ha podido eliminar la aptitud";
        }
    }

}
