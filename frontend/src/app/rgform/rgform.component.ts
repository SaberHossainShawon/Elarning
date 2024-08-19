import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormBuilder, FormsModule, NgForm, ReactiveFormsModule, Validators } from '@angular/forms';
import { FormComponent } from '../paymentform/form.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ServiceService } from '../service/service.service';
import { HttpClientModule } from '@angular/common/http';
import { ResponseStatus } from '../response/respnse';
import { NavigationEnd, Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-rgform',
  standalone: true,
  imports: [FormsModule, CommonModule, FormComponent, ReactiveFormsModule, MatFormFieldModule, MatSelectModule, HttpClientModule,RouterModule],
  templateUrl: './rgform.component.html',
  styleUrl: './rgform.component.scss',
  providers: [
    ServiceService
  ]
})
export class RgformComponent {
  constructor(private formBuilder: FormBuilder, private snackBar: MatSnackBar, private service: ServiceService, private router: Router) { }
  // @Input() isVisible = false;
  selected: string = 'red';
  activeRoute: string = '';
  imageUrl: string | ArrayBuffer | null = null;
  imageBase64: string | null = null; 

  course: any = {};

  studentForm = this.formBuilder.group({
    name: [''],
    address: [''],
    phonenumber: [''],
    age: [''],
    image: ['', [Validators.required]], 
    user: this.formBuilder.group({
      username: ['', Validators.required],
      name: [''],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(2)]]
    })
  })

  onSubmit() {
    if (this.studentForm.valid) {
      const data = this.studentForm.value as any;
      if (this.imageBase64) {
        data.image = this.imageBase64;
      }
      if (data.user) {
        data.user.name = data.name;
      }
      if (data.coursename) {
        data.coursename = data.coursename.map((courseId: any) => {
          return courseId.id ? courseId : { id: courseId };
        });
      }
      //this.router.navigate(['/paymentform']);

      // if (data.coursename) {
      //   data.coursename = data.coursename.map((courseId: string) => {
      //     return this.courses.find(c => c.id === parseInt(courseId))?.name || '';
      //   });
      // } else {
      //   data.coursename = [];
      // }
      // console.log(data)
      this.service.save(data, "student").subscribe((data) => {
        console.log(data)
        this.snackBar.open(data.message, 'Close', {
          duration: 3000

        });
        if (data.status == ResponseStatus.SUCCESS) {
          this.router.navigate(['/paymentform']);
          }

        // if (data.status == ResponseStatus.SUCCESS) {
        //   this.showPaymentForm.emit();
        //   this.isVisible = false;
        // }
       })
      // this.showPaymentForm.emit();
      //   this.isVisible = false;
    }
  }

  ngOnInit(): void {
    const coursesData = sessionStorage.getItem('coursedata');
    if (coursesData) {
      this.course = JSON.parse(coursesData);
    }
  }

  // @Output() showPaymentForm = new EventEmitter<void>();

  

  get username() {
    return this.studentForm.get('username')
  }
  get email() {
    return this.studentForm.get('email')
  }

  get password() {
    return this.studentForm.get('password')
  }
  get courseName() {
    return this.studentForm.get('coursename')
  }
  get photo() {
    return this.studentForm.get('image')
  }

  onFileChange(event: any) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.studentForm.patchValue({
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
}
