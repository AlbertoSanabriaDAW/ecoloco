package com.example.ecoloco.repositorios;

import com.example.ecoloco.modelos.AptitudEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AptitudEventoRepository extends JpaRepository<AptitudEvento, Integer> {

}