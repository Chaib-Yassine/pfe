import { TestBed } from '@angular/core/testing';

import { TypeBadgeService } from './type-badge.service';

describe('TypeBadgeService', () => {
  let service: TypeBadgeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TypeBadgeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
