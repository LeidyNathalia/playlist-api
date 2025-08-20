package com.playlist.demo.repository;

import com.playlist.demo.entity.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para operaciones CRUD sobre cancion
 */

@Repository
public interface CancionRepository extends JpaRepository<Cancion, Long> {

    Optional<Cancion> findByName(String name);

}
