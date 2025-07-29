import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ListaReproduccion } from '../models/lista-reproduccion.model';

@Injectable({ providedIn: 'root' })
export class ListaService {
  private apiUrl = 'http://localhost:8080/lists';

  constructor(private http: HttpClient) {}

  crearLista(lista: ListaReproduccion): Observable<any> {
    return this.http.post(this.apiUrl, lista, { observe: 'response' });
  }

  obtenerListas(): Observable<ListaReproduccion[]> {
    return this.http.get<ListaReproduccion[]>(this.apiUrl);
  }

  obtenerLista(nombre: string): Observable<ListaReproduccion> {
    return this.http.get<ListaReproduccion>(`${this.apiUrl}/${nombre}`);
  }

  eliminarLista(nombre: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${nombre}`);
  }
}
