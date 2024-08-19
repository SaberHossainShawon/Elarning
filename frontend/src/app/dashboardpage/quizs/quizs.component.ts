import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormArray, FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { ServiceService } from '../../service/service.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-quizs',
  standalone: true,
  imports: [ReactiveFormsModule, HttpClientModule, CommonModule],
  templateUrl: './quizs.component.html',
  styleUrl: './quizs.component.scss',
  providers: [ServiceService],
})
export class QuizsComponent {
  constructor(private fb: FormBuilder, private service: ServiceService,private matSnack:MatSnackBar) { }


  quizForm=this.fb.group({
    question: [''],
    options: this.fb.array([this.fb.control('')]),
    answer: ['']
  })
  onSubmit() {
    console.log(this.quizForm.value);
    const data = this.quizForm.value as any;
    this.service.save(data, "quiz").subscribe((data) => {
      this.matSnack.open(data.message, 'Close', {
        duration: 3000
      })
    })
  }
  get options(): FormArray {
    return this.quizForm.get('options') as FormArray;
  }

  addOption(): void {
    this.options.push(this.fb.control(''));
  }

}
