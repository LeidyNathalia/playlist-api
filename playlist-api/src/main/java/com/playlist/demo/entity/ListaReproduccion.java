package com.playlist.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad que representa una lista de reproducción.
 */


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListaReproduccion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String nombre;
	
	private String descripcion;
	
	/**
	 * Relacion uno a muchos con canciones.
	 * Cascada ALL permite que al guardar la lista, también se guarden las canciones, igual con update y delete.
	 * mappedBy indica el campo que es dueño de lsa relación  dentro de la clase canción.
	 */

	@OneToMany(mappedBy = "listaReproduccion", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<Cancion> canciones;
}
