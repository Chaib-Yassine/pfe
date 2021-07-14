import { async, ComponentFixture, TestBed } from '@angular/core/testing';


import {BadgeMultiImpressionComponent} from './badgeMultiImpression.component';




describe('BadgeMultiImpressionComponent', () => {
  let component: BadgeMultiImpressionComponent;
  let fixture: ComponentFixture<BadgeMultiImpressionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BadgeMultiImpressionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BadgeMultiImpressionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
