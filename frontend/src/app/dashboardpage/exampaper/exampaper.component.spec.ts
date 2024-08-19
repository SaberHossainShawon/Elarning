import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExampaperComponent } from './exampaper.component';

describe('ExampaperComponent', () => {
  let component: ExampaperComponent;
  let fixture: ComponentFixture<ExampaperComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExampaperComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ExampaperComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
