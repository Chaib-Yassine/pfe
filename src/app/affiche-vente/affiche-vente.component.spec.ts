import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AfficheVenteComponent } from './affiche-vente.component';

describe('AfficheVenteComponent', () => {
  let component: AfficheVenteComponent;
  let fixture: ComponentFixture<AfficheVenteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AfficheVenteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AfficheVenteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
