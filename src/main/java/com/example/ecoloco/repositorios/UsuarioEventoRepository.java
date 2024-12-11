package com.example.ecoloco.repositorios;

import com.example.ecoloco.modelos.UsuarioEvento;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioEventoRepository extends JpaRepository<UsuarioEvento, Integer> {

    //Metodo para eliminar usuario de todos los eventos
    @Modifying
    @Transactional
    @Query("DELETE FROM UsuarioEvento ue WHERE ue.usuario.id = :idUsuario")
    void eliminarPorUsuarioId(@Param("idUsuario") Integer idUsuario);

    //Metodo para eliminar usuario de evento (darse de baja)
    @Modifying
    @Transactional
    @Query("DELETE FROM UsuarioEvento ue WHERE ue.usuario.id = :idUsuario AND ue.evento.id = :idEvento")
    void eliminarUsuarioDeEvento(@Param("idUsuario") Integer idUsuario, @Param("idEvento") Integer idEvento);

    Optional<UsuarioEvento> findByEventoIdAndUsuarioId(Integer idEvento, Integer idUsuario);

    List<UsuarioEvento> findByUsuarioId(Integer idUsuario);
}
