import { async, ComponentFixture, TestBed } from '@angular/core/testing';


import {TicketMultiImpressionComponent} from './ticketMultiImpression.component';




describe('BadgeMultiImpressionComponent', () => {
  let component: TicketMultiImpressionComponent;
  let fixture: ComponentFixture<TicketMultiImpressionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TicketMultiImpressionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TicketMultiImpressionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
