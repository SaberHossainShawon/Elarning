import { Component } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { CommonModule } from '@angular/common';
import { ServiceService } from '../service/service.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-courseform',
  standalone: true,
  imports: [ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    CommonModule,
    HttpClientModule
  ],
  templateUrl: './courseform.component.html',
  styleUrls: ['./courseform.component.scss'],
  providers: [ServiceService],
})
export class CourseformComponent {
  constructor(private formBuilder: FormBuilder, private service: ServiceService, private snackBar: MatSnackBar) { }
  ratings: string[] = ["1", "2", "3", "4", "5"];
  imageUrl: string | ArrayBuffer | null = null;
  imageBase64: string | null = null; 

  courseForm = this.formBuilder.group({
    image: [null],
    pricing: ['', Validators.required],
    rating: ['', Validators.required],
    courseName: ['', Validators.required],
    traineeName: ['', Validators.required],
    courseDuration: ['', Validators.required],
    totalStudents: ['', Validators.required]
  });

  onFileChange(event: any) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.courseForm.patchValue({
        image: file
      });
      const reader = new FileReader();
      reader.onload = () => {
        this.imageUrl = reader.result;
        this.imageBase64 = reader.result as string; 
      };
      reader.readAsDataURL(file);
    }
  }
  

  onSubmit() {
    if (this.courseForm.valid) {
      const data = this.courseForm.value as any;
      if (this.imageBase64) {
        data.image = this.imageBase64;
      }
      this.service.save(data, "course").subscribe((response) => {
        console.log(response);
        this.snackBar.open(response.message, 'Close', {
          duration: 3000
        });
      });
    }
  }
}
