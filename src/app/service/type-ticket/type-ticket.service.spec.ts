import { TestBed } from '@angular/core/testing';


import {TypeTicketService} from './type-ticket.service';

describe('TypeTicketService', () => {
  let service: TypeTicketService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TypeTicketService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
