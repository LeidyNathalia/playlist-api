import { Component, OnInit } from '@angular/core';
import { ListaService } from '../../services/lista.service';
import { ListaReproduccion } from '../../models/lista-reproduccion.model';

@Component({
  selector: 'app-lista-listado',
  templateUrl: './lista-listado.component.html',
  styleUrls: ['./lista-listado.component.css'],
  standalone: false
})
export class ListaListadoComponent implements OnInit {
  listas: ListaReproduccion[] = [];

  constructor(private listaService: ListaService) {}

  ngOnInit() {
    this.listaService.obtenerListas().subscribe(data => this.listas = data);
  }

  eliminar(nombre: string) {
    this.listaService.eliminarLista(nombre).subscribe(() => {
      this.listas = this.listas.filter(l => l.nombre !== nombre);
    });
  }
}
