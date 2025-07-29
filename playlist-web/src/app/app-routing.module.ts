import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListaFormComponent } from './components/list-form/lista-form.component';
import { ListaListadoComponent } from './components/lista-listado/lista-listado.component';
import { ListaDetalleComponent } from './components/lista-detalle/lista-detalle.component';

const routes: Routes = [
  { path: '', component: ListaListadoComponent },
  { path: 'crear', component: ListaFormComponent },
  { path: 'lista/:nombre', component: ListaDetalleComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
