import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RgformComponent } from './rgform.component';

describe('RgformComponent', () => {
  let component: RgformComponent;
  let fixture: ComponentFixture<RgformComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RgformComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RgformComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
