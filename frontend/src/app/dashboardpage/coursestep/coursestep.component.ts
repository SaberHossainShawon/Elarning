import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { ServiceService } from '../../service/service.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-coursestep',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, HttpClientModule, MatFormFieldModule, MatSelectModule, MatInputModule],
  templateUrl: './coursestep.component.html',
  styleUrls: ['./coursestep.component.scss'],
  providers: [ServiceService],
})
export class CoursestepComponent {

  contentForm = this.fb.group({
    course: [''],
    // contanttype: [''],  // এটা এখন একটা array হিসেবে থাকবে, যাতে একাধিক টাইপ সিলেক্ট করা যায়
    content: [''],
    stepnumber: [''],
    quizExamPapers: [''],
    videoLink: [''],

  });

  constructor(private service: ServiceService, private matSnack: MatSnackBar, private fb: FormBuilder) { }

  courses: any[] = [];
  examPapers: any[] = [];
  contentTypes: string[] = ['HTML', 'QUIZ', 'Text', 'Video'];

  showExamPaperField = false;
  showContentField = false;
  showVideoLinkField = false;

  ngOnInit(): void {
    this.service.findAll("exampaper").subscribe((data) => {
      this.examPapers = data.data;
    });

    this.service.findAll("course").subscribe((data) => {
      this.courses = data.data;
      //console.log(data.data);
    });
  }

  onContentTypeChange(selectedTypes: string[]): void {
    this.showExamPaperField = selectedTypes.includes('QUIZ');
    this.showContentField = selectedTypes.includes('HTML') || selectedTypes.includes('Text');
    this.showVideoLinkField = selectedTypes.includes('Video');
  }

  onSubmit() {
    const formdata = this.contentForm.value;
    // if (data.contanttype.includes('QUIZ')) {
    //   data.quizExamPapers = [{ id: data.quizExamPapers }];
    // }

    // if (data.course) {
    //   data.course = { id: data.course }; 
    // }

    const data = { ...formdata, course: { id: formdata.course },quizExamPapers:{id:formdata.quizExamPapers} }

    console.log(data)

    this.service.save(data, "coursestep").subscribe((data) => {
      this.matSnack.open(data.message, 'Close', {
        duration: 3000
      })
    })
  }
}
