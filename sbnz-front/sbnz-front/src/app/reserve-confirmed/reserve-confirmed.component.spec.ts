import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReserveConfirmedComponent } from './reserve-confirmed.component';

describe('ReserveConfirmedComponent', () => {
  let component: ReserveConfirmedComponent;
  let fixture: ComponentFixture<ReserveConfirmedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReserveConfirmedComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReserveConfirmedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
