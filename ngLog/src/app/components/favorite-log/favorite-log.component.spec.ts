import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FavoriteLogComponent } from './favorite-log.component';

describe('FavoriteLogComponent', () => {
  let component: FavoriteLogComponent;
  let fixture: ComponentFixture<FavoriteLogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FavoriteLogComponent]
    });
    fixture = TestBed.createComponent(FavoriteLogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
