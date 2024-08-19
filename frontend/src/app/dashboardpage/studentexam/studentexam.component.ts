import { Component } from '@angular/core';
import { FormBuilder, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatStepperModule } from '@angular/material/stepper';
import { MatButtonModule } from '@angular/material/button';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { ServiceService } from '../../service/service.service';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-studentexam',
  standalone: true,
  imports: [
    MatButtonModule,
    MatStepperModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    CommonModule,
    HttpClientModule,
  ],
  templateUrl: './studentexam.component.html',
  styleUrl: './studentexam.component.scss',
  providers: [ServiceService]
})
export class StudentexamComponent {
  firstFormGroup = this._formBuilder.group({
    firstCtrl: ['', Validators.required],
  });
  secondFormGroup = this._formBuilder.group({
    secondCtrl: ['', Validators.required],
  });
  thirdFormGroup = this._formBuilder.group({
    secondCtrl: ['', Validators.required],
  });
  isLinear = false;
  videoLink: SafeResourceUrl | null = null;
  documentContent: string | null = null;
  quizzes: any[] = [];

  constructor(
    private _formBuilder: FormBuilder,
    private sanitizer: DomSanitizer,
    private service: ServiceService
  ) { }

  ngOnInit(){
    this.service.findAll('coursestep').subscribe(data => {
     // const youtubeUrl = data.data.videoLink;
      //     this.videoLink = this.sanitizer.bypassSecurityTrustResourceUrl(youtubeUrl);
      //     this.documentContent = data.data.content;     
      //     this.quizzes = data.data;
      console.log(data.data)
      console.log(data.data.content)
    });
  }
}
