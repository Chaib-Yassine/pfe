import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import {BadgeImpressionComponent} from './badgeImpression.component';




describe('BadgeComponent', () => {
  let component: BadgeImpressionComponent;
  let fixture: ComponentFixture<BadgeImpressionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BadgeImpressionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BadgeImpressionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
