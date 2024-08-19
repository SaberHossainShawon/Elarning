import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoursestepComponent } from './coursestep.component';

describe('CoursestepComponent', () => {
  let component: CoursestepComponent;
  let fixture: ComponentFixture<CoursestepComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CoursestepComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CoursestepComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
