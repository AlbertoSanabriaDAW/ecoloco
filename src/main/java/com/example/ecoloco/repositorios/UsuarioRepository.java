package com.example.ecoloco.repositorios;

import com.example.ecoloco.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    //Listar todos los usuarios
    List<Usuario> findAllByDeletedFalse();

    //Buscar usuario por nombre de usuario
    Optional<Usuario> findByUsernameAndDeletedFalse(String username);

    //Buscar usuario por id
    Optional<Usuario> findByIdAndDeletedFalse(Integer id);

    //Buscar usuario por correo
    Optional<Usuario> findByEmailAndDeletedFalse(String email);

    //Verificar si existe un usuario con el mismo nombre de usuario
    boolean existsByUsernameAndDeletedFalse(String username);

    //Verificar si existe un usuario con el mismo correo
    boolean existsByEmailAndDeletedFalse(String email);
}
