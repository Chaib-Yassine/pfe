import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InterfaceVenteComponent } from './interface-vente.component';

describe('AfficheVenteComponent', () => {
  let component: InterfaceVenteComponent;
  let fixture: ComponentFixture<InterfaceVenteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InterfaceVenteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InterfaceVenteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
