package com.playlist.demo.service;


import com.playlist.demo.entity.Cancion;
import com.playlist.demo.repository.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancionService {

    @Autowired
    private CancionRepository cancionRepository;

    public Cancion findByNombre(String nombre){
        return cancionRepository.findByName(nombre).get();
        //map(cancion -> cancion.getListaReproduccion());
    }
}
