package com.example.ecoloco.repositorios;

import com.example.ecoloco.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    //Buscar usuario por nombre de usuario
    Optional<Usuario> findByUsername(String username);

    //Buscar usuario por correo
    Optional<Usuario> findByEmail(String email);

    //Verificar si existe un usuario con el mismo nombre de usuario
    boolean existsByUsername(String username);

    //Verificar si existe un usuario con el mismo correo
    boolean existsByEmail(String email);
}
