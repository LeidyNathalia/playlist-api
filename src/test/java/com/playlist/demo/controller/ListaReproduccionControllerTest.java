package com.playlist.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.playlist.demo.entity.ListaReproduccion;
import com.playlist.demo.security.JwtAuthFilter;
import com.playlist.demo.security.JwtService;
import com.playlist.demo.service.ListaReproduccionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Pruebas unitarias para el controlador ListaReproduccionController.
 */
@WebMvcTest(ListaReproduccionController.class)
@AutoConfigureMockMvc(addFilters = false) //Desactiva filtros de seguridad
public class ListaReproduccionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private JwtAuthFilter jwtAuthFilter;

    @MockBean
    private ListaReproduccionService listaReproduccionService;

    private ObjectMapper objectMapper;

    private static final Logger logger = LoggerFactory.getLogger(ListaReproduccionControllerTest.class);

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    /**
     * Crear lista de reproduccion exitosa
     * @throws Exception
     */

    @Test
    void testCrearLista_Exitosa() throws Exception {
        logger.info("Iniciando testCrearLista_Exitosa");
        ListaReproduccion lista = new ListaReproduccion();
        lista.setNombre("Favoritas");
        lista.setDescripcion("canciones favoritas");

        when(listaReproduccionService.createListaReproduccion(any()))
                .thenReturn(Optional.of(lista));

        mockMvc.perform(post("/lists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(lista)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Favoritas")); //Verifica contenido del Json
        logger.info("Finaliza testCrearLista_Exitosa exitosamente");
    }


    /**
     * Test: Intentar crear una lista con nombre nulo.
     * Se espera un error 400 Bad Request con mensaje personalizado.
     */
    @Test
    void testCrearLista_NombreNulo() throws Exception {
        ListaReproduccion lista = new ListaReproduccion();
        lista.setNombre(null);

        when(listaReproduccionService.createListaReproduccion(any()))
                .thenReturn(Optional.empty());

        mockMvc.perform(post("/lists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(lista)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("El nombre de la lista no puede ser nulo"));
    }

    /**
     * Test: Obtener todas las listas de reproducción.
     */
    @Test
    void testObtenerTodasLasListas() throws Exception {
        ListaReproduccion lista = new ListaReproduccion();
        lista.setNombre("Rock");
        lista.setDescripcion("Rock clásico");

        when(listaReproduccionService.getAllListasReproduccion())
                .thenReturn(List.of(lista));

        mockMvc.perform(get("/lists"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Rock"));
    }

    /**
     * Test: Obtener una lista por nombre (caso exitoso).
     */
    @Test
    void testObtenerListaPorNombre_Existe() throws Exception {
        ListaReproduccion lista = new ListaReproduccion();
        lista.setNombre("Pop");

        when(listaReproduccionService.getListaReproduccionByName("Pop"))
                .thenReturn(Optional.of(lista));

        mockMvc.perform(get("/lists/Pop"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Pop"));
    }

    /**
     * Test: Obtener una lista por nombre (caso NO exitoso).
     */
    @Test
    void testObtenerListaPorNombre_NoExiste() throws Exception {
        when(listaReproduccionService.getListaReproduccionByName("Inexistente"))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/lists/Inexistente"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testEliminarLista_Existe() throws Exception {
        when(listaReproduccionService.deletePlaylist("Rock"))
                .thenReturn(true);

        mockMvc.perform(delete("/lists/Rock"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testEliminarLista_NoExiste() throws Exception {
        when(listaReproduccionService.deletePlaylist("NoExiste"))
                .thenReturn(false);

        mockMvc.perform(delete("/lists/NoExiste"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Lista no encontrada"));
    }
}