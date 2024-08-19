import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-homedashboard',
  standalone: true,
  imports: [CommonModule,RouterModule],
  templateUrl: './homedashboard.component.html',
  styleUrl: './homedashboard.component.scss'
})
export class HomedashboardComponent {

  constructor(private router: Router) { }

  enrolledCourses: any[] = [];

  ngOnInit() {
    const storedCourses = localStorage.getItem('enrolledCourses');
    if (storedCourses) {
      this.enrolledCourses = JSON.parse(storedCourses);
    }
  }

  
  counter(i: number) {
    return new Array(Number(i));
  }

  navigateToExamPaper(courseId: number) {
    localStorage.setItem('selectedCourseId', courseId.toString());
    this.router.navigate(['/studentexampaper']);
  }

}
