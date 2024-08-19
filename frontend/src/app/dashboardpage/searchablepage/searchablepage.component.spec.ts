import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchablepageComponent } from './searchablepage.component';

describe('SearchablepageComponent', () => {
  let component: SearchablepageComponent;
  let fixture: ComponentFixture<SearchablepageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SearchablepageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SearchablepageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
