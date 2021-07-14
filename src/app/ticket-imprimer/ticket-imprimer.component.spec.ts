import { async, ComponentFixture, TestBed } from '@angular/core/testing';


import {TicketImprimerComponent} from './ticket-imprimer.component';

describe('ticketImprimerComponent', () => {
  let component: TicketImprimerComponent;
  let fixture: ComponentFixture<TicketImprimerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TicketImprimerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TicketImprimerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
