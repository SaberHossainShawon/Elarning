import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentexampaperComponent } from './studentexampaper.component';

describe('StudentexampaperComponent', () => {
  let component: StudentexampaperComponent;
  let fixture: ComponentFixture<StudentexampaperComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StudentexampaperComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(StudentexampaperComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
