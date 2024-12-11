package com.example.ecoloco.repositorios;

import com.example.ecoloco.modelos.Aptitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AptitudRepository extends JpaRepository<Aptitud, Integer> {

}
