package com.bootcampdelloite.desafiospringboot.Repository;


import com.bootcampdelloite.desafiospringboot.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmailIgnoreCase(String email);
    boolean existsByNomeIgnoreCase(String nome);
    Optional<Usuario> findById(Long id);
}
