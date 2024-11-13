package com.example.ecoloco.servicios;

import com.example.ecoloco.modelos.AptitudEvento;
import com.example.ecoloco.repositorios.AptitudEventoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AptitudEventoService {

    @Autowired
    private AptitudEventoRepository aptitudEventoRepository;

    public List<AptitudEvento> findAllAptitudEventos() {
        return aptitudEventoRepository.findAll();
    }

    public AptitudEvento findAptitudEventosById(Integer id) {
        return aptitudEventoRepository.findById(id).orElse(null);
    }

    public AptitudEvento guardarAptitudEventos(AptitudEvento aptitudEvento) {
        return aptitudEventoRepository.save(aptitudEvento);
    }

    public String eliminarAptitudEventos(Integer id) {
        try {
            aptitudEventoRepository.deleteById(id);
            return "AptitudEvento eliminado";
        } catch (Exception e) {
            return "No se ha podido eliminar aptitudEvento";
        }
    }

}
