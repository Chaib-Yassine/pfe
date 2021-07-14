import { async, ComponentFixture, TestBed } from '@angular/core/testing';


import {TypeTicketComponent} from './type-ticket.component';

describe('TypeTicketComponent', () => {
  let component: TypeTicketComponent;
  let fixture: ComponentFixture<TypeTicketComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TypeTicketComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TypeTicketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
