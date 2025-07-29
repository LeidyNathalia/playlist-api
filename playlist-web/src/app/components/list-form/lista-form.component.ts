import { Component } from '@angular/core';
import { ListaService } from '../../services/lista.service';
import { ListaReproduccion } from '../../models/lista-reproduccion.model';
import { Cancion } from '../../models/cancion.model';

@Component({
  selector: 'app-lista-form',
  templateUrl: './lista-form.component.html',
  styleUrls: ['./lista-form.component.css'],
  standalone: false
})
export class ListaFormComponent {
  lista: ListaReproduccion = {
    nombre: '',
    descripcion: '',
    canciones: []
  };

  nuevaCancion: Cancion = {
    titulo: '',
    artista: '',
    album: '',
    anno: '',
    genero: ''
  };

  constructor(private listaService: ListaService) {}

  agregarCancion() {
    this.lista.canciones.push({ ...this.nuevaCancion });
    this.nuevaCancion = { titulo: '', artista: '', album: '', anno: '', genero: '' };
  }

  crearLista() {
    this.listaService.crearLista(this.lista).subscribe({
      next: () => {
        alert('Lista creada');
        this.lista = { nombre: '', descripcion: '', canciones: [] };
      },
      error: () => alert('Error al crear la lista')
    });
  }
}
