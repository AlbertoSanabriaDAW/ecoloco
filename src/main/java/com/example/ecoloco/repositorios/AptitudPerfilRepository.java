package com.example.ecoloco.repositorios;

import com.example.ecoloco.modelos.AptitudPerfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AptitudPerfilRepository extends JpaRepository<AptitudPerfil, Integer> {

}
