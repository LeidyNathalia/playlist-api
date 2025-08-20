package com.playlist.demo.controller;


import com.playlist.demo.entity.Cancion;
import com.playlist.demo.entity.ListaReproduccion;
import com.playlist.demo.service.CancionService;
import com.playlist.demo.service.ListaReproduccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para manejar operaciones sobre listas de reproducción.
 */
@RestController
@RequestMapping("/lists")
public class ListaReproduccionController {

    @Autowired
    private ListaReproduccionService listaReproduccionService;

    @Autowired
    private CancionService cancionService;

    /**
     * Crear una nueva lista de reproducción.
     */
    @PostMapping
    public ResponseEntity<?> createPlaylist(@RequestBody ListaReproduccion listaReproduccion) {
        Optional<ListaReproduccion> result = listaReproduccionService.createListaReproduccion(listaReproduccion);
        if (result.isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre de la lista no puede ser nulo");
        }
        URI location = URI.create("/lists/" + listaReproduccion.getNombre());
        return ResponseEntity.created(location).body(result.get());
    }

    /**
     * Obtener todas las listas existentes.
     */
    @GetMapping
    public ResponseEntity<List<ListaReproduccion>> getAll() {
        return ResponseEntity.ok(listaReproduccionService.getAllListasReproduccion());
    }


    /**
     * Obtener una lista por su nombre.
     */
    @GetMapping("/{nombre}")
    public ResponseEntity<ListaReproduccion> getByName(@PathVariable String nombre) {
        return listaReproduccionService.getListaReproduccionByName(nombre)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
                //.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lista no encontrada"));
    }

    // Buscar nombre de la cancion y retornar lista de reproducción

   @GetMapping("/buscarPlayList")
   public Cancion searchCancion(@RequestParam String name){
        return cancionService.findByNombre(name);
   }

    /**
     * Eliminar una lista por su nombre.
     */
    @DeleteMapping("/{nombre}")
    public ResponseEntity<?> deleteByName(@PathVariable String nombre) {
        boolean deleted = listaReproduccionService.deletePlaylist(nombre);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista no encontrada");
        }
    }

    /**
 * Buscar una canción por nombre y devolver la lista o listas de reproducción donde aparece.
 */
    @GetMapping("/cancion/{nombreCancion}")
    public ResponseEntity<?> getListaByCancion(@PathVariable String nombreCancion) {
        List<ListaReproduccion> listas = listaReproduccionService.findListasByCancion(nombreCancion);

        if (listas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("La canción '" + nombreCancion + "' no pertenece a ninguna lista de reproducción");
        }

        return ResponseEntity.ok(listas);
    }

}
