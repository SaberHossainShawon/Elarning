import { Component } from '@angular/core';
import { SearchablepageComponent } from "../searchablepage/searchablepage.component";
import { ServiceService } from '../../service/service.service';
import { IDropdownProps } from '../searchablepage/searchpage.model';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormField, MatFormFieldModule, MatLabel } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { format } from 'date-fns';
import { HttpClientModule } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-exampaper',
  standalone: true,
  imports: [SearchablepageComponent, CommonModule, FormsModule, ReactiveFormsModule, MatFormFieldModule,
    MatInputModule, MatButtonModule, MatDatepickerModule,
    MatNativeDateModule, HttpClientModule],
  templateUrl: './exampaper.component.html',
  styleUrl: './exampaper.component.scss',
  providers: [
    ServiceService
  ]
})
export class ExampaperComponent {
  selectedValues: any[] = [];
  quizes: any[] = [];
  select: any[] = [];

  examDate: Date | null = null;
  totalNumber: number = 0;
  subject: string = '';
  time: string = '';
  examduration: string = '';
  title: string = '';

  props: IDropdownProps = { key: '', text: 'question', value: 'id' };

  constructor(private service: ServiceService, private matSnack: MatSnackBar) { }

  ngOnInit() {
    this.service.findAll('quiz').subscribe((data) => {
      this.quizes = data.data;
    })
  }

  onSubmit() {
    // console.log(this.selectedValues);
    const formData = {
      examdate: this.examDate ? format(this.examDate, 'yyyy-MM-dd') : null,
      totalnumber: this.totalNumber,
      subject: this.subject,
      examtime: this.time,
      title: this.title,
      questions: this.select,
      examduration: this.examduration,
      // selectedValues: this.selectedValues,

    };

    // if (this.examDate) {
    //   const formattedDate = this.examDate.toISOString(); // Format as needed for backend
    //   console.log('Selected Exam Date:', formattedDate);
    // }

    console.log('Form Data:', formData);
    
    

    this.service.save(formData, "exampaper").subscribe((data) => {
      console.log(data);
      this.matSnack.open(data.message, 'Close', {
        duration: 3000
      })
    })
  }

  onSectionChange(section: any) {
    console.log(section)
    this.select = this.quizes.filter(quiz => this.selectedValues.includes(quiz.id));
  }

  trackByQuiz(index: number, quiz: any) {
    return quiz ? quiz.id : undefined;
  }
}
