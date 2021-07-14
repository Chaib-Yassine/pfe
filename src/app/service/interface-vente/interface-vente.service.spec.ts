import { TestBed } from '@angular/core/testing';

import { InterfaceVenteService } from './interface-vente.service';

describe('TypeBadgeService', () => {
  let service: InterfaceVenteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InterfaceVenteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
