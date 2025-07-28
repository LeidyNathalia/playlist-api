package com.playlist.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;


/**
 * Entidad Cancion.
 * Datos básicos de la canción.
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cancion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String 	titulo;
	
	private String 	artista;
	
	private String 	album;
	
	private String 	anno;
	
	private String 	genero;


	@ManyToOne
	@JoinColumn(name = "lista_reproduccion_id")
	@JsonBackReference
	private ListaReproduccion listaReproduccion;
	
}
