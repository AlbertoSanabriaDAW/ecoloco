package com.example.ecoloco.repositorios;

import com.example.ecoloco.dtos.EventoDTO;
import com.example.ecoloco.modelos.Evento;
import com.example.ecoloco.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

    //Lista todos los eventos
    List<Evento> findAllByDeletedFalse();

    //Buscar evento por id
    Optional<Evento> findByIdAndDeletedFalse(Integer integer);

    //Buscar evento por nombre
    Optional<Evento> findByTituloAndDeletedFalse(String nombre);

}
