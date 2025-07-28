package com.playlist.demo.repository;

import com.playlist.demo.entity.ListaReproduccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Repositorio para operaciones CRUD sobre ListaReproduccion.
 */
@Repository
public interface ListaReproduccionRepository extends JpaRepository<ListaReproduccion,Long> {

    Optional<ListaReproduccion> findByNombre(String nombre);

    boolean existsByNombre(String nombre);

    void deleteByNombre(String nombre);
}
