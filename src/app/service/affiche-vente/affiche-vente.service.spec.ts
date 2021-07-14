import { TestBed } from '@angular/core/testing';

import { AfficheVenteService } from './affiche-vente.service';

describe('TypeBadgeService', () => {
  let service: AfficheVenteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AfficheVenteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
