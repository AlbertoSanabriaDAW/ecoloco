package com.example.ecoloco.mappers;

import com.example.ecoloco.dtos.EventoDTO;
import com.example.ecoloco.modelos.Evento;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventoMapper {

    /**
     * Metodo para convertir un EventoDTO a Evento
     * @param eventoDTO
     * @return
     */
    Evento toEntity(EventoDTO eventoDTO);

    /**
     * Metodo para convertir un Evento a EventoDTO
     * @param evento
     * @return
     */
    EventoDTO toDTO(Evento evento);

    /**
     * Metodo para convertir una lista de EventoDTO a Evento
     */

    List<Evento> toEntity(List<EventoDTO> dtos);

    /**
     * Metodo para convertir una lista de Evento a EventoDTO
     */

    List<EventoDTO> toDTO(List<Evento> entities);

}
