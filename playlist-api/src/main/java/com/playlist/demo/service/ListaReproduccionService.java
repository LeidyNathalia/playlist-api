package com.playlist.demo.service;


import com.playlist.demo.entity.ListaReproduccion;
import com.playlist.demo.repository.ListaReproduccionRepository ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Servicio encargado de manejar la lógica relacionada con listas de reproducción.
 */
@Service
public class ListaReproduccionService {

    @Autowired
    private ListaReproduccionRepository listaReproduccionRepository;

    /**
     * Crea una nueva lista de reproducción si el nombre es válido.
     */
    public Optional<ListaReproduccion> createListaReproduccion(ListaReproduccion listaReproduccion){
        if (listaReproduccion.getNombre() == null || listaReproduccion.getNombre().trim().isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(listaReproduccionRepository.save(listaReproduccion));
    }

    /**
     * Retorna todas las listas de reproducción almacenadas.
     */
    public List<ListaReproduccion> getAllListasReproduccion() {
        return listaReproduccionRepository.findAll();
    }

    /**
     * Busca una lista de reproducción por su nombre.
     */
    public Optional<ListaReproduccion> getListaReproduccionByName(String nombre) {
        return listaReproduccionRepository.findByNombre(nombre);
    }

    /**
     * Elimina una lista de reproducción por su nombre.
     */
    @Transactional
    public boolean deletePlaylist(String nombre) {
        if (!listaReproduccionRepository.existsByNombre(nombre)) {
            return false;
        }
        listaReproduccionRepository.deleteByNombre(nombre);
        return true;
    }

}
