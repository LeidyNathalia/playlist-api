import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ListaService } from '../../services/lista.service';
import { ListaReproduccion } from '../../models/lista-reproduccion.model';

@Component({
  selector: 'app-lista-detalle',
  templateUrl: './lista-detalle.component.html',
  styleUrls: ['./lista-detalle.component.css'],
  standalone: false
})
export class ListaDetalleComponent implements OnInit {
  lista?: ListaReproduccion;

  constructor(
    private route: ActivatedRoute,
    private listaService: ListaService
  ) {}

  ngOnInit() {
    const nombre = this.route.snapshot.paramMap.get('nombre');
    if (nombre) {
      this.listaService.obtenerLista(nombre).subscribe(data => this.lista = data);
    }
  }
}
