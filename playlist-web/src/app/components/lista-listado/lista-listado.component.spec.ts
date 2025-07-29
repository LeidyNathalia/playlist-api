import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaListado } from './lista-listado.component';

describe('ListaListado', () => {
  let component: ListaListado;
  let fixture: ComponentFixture<ListaListado>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListaListado]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListaListado);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
