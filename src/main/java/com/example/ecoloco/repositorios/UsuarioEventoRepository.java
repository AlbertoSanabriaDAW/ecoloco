package com.example.ecoloco.repositorios;

import com.example.ecoloco.modelos.UsuarioEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioEventoRepository extends JpaRepository<UsuarioEvento, Integer> {

}
