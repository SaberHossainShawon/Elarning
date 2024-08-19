import { Component } from '@angular/core';
import { FormComponent } from '../paymentform/form.component';
import { RgformComponent } from '../rgform/rgform.component';
import { Router, RouterModule } from '@angular/router';

import { ServiceService } from '../service/service.service';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-course',
  standalone: true,
  imports: [ CommonModule,RgformComponent, FormComponent, RouterModule,HttpClientModule],
  templateUrl: './course.component.html',
  styleUrl: './course.component.scss',
  providers: [ServiceService],
})
export class CourseComponent {
  isRegisterFormVisible = false;
  isPaymentFormVisible = false;
  courses: any[] = [];
  enrolledCourses: any[] = [];
  saveCourses(index: number): void {
    sessionStorage.setItem('coursedata', JSON.stringify(this.courses[index]));
   
   const courseData = this.courses[index];
   console.log('Selected Course:', courseData);
   let existingCourses = JSON.parse(localStorage.getItem('enrolledCourses') || '[]');
   console.log('Existing Courses:', existingCourses);
   existingCourses.push(courseData);
   console.log('Updated Course List:', existingCourses);
   localStorage.setItem('enrolledCourses', JSON.stringify(existingCourses));


    if (this.checkSessionStorage()) {
      this.router.navigate(['/login']);
    }
  }
  checkSessionStorage(): boolean {
    const data = sessionStorage.getItem('coursedata');
   
    
    return !!data;
  }
  

  showRegisterForm() {
    this.isRegisterFormVisible = true;
  }

  showPaymentForm() {
    this.isPaymentFormVisible = true;
  }
  hidePaymentForm() {
    this.isPaymentFormVisible = false;
  }

  ngOnInit() {
    this.service.findAll("course").subscribe((data) => {
      this.courses = data.data
    })

    this.getEnrolledCourses();
    console.log('All Enrolled Courses:', this.getAllCourses());
  }

  counter(i: number) {
    return new Array(Number(i));
  }

  getEnrolledCourses() {
    const courses = localStorage.getItem('enrolledCourses');
    if (courses) {
      this.enrolledCourses = JSON.parse(courses);
    }
  }
  getAllCourses() {
    return JSON.parse(localStorage.getItem('enrolledCourses') || '[]');
}

  constructor(private service: ServiceService,private router:Router) { }

}
