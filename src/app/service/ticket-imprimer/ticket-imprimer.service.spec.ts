import { TestBed } from '@angular/core/testing';


import {TicketImprimerService} from './ticket-imprimer.service';

describe('TicketImprimerService', () => {
  let service: TicketImprimerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TicketImprimerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
