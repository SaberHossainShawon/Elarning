import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ServiceService } from '../../service/service.service';
import { MatStepperModule } from '@angular/material/stepper';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-studentexampaper',
  standalone: true,
  imports: [HttpClientModule, CommonModule, FormsModule, ReactiveFormsModule, MatButtonModule,
    MatStepperModule, MatFormFieldModule, MatInputModule,],
  templateUrl: './studentexampaper.component.html',
  styleUrl: './studentexampaper.component.scss',
  providers: [ServiceService],
})
export class StudentexampaperComponent {
 



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
  videoLink: SafeResourceUrl = "";
  documentContent: string = "";
  quizExamPapers: any;

  constructor(
    private _formBuilder: FormBuilder,
    private sanitizer: DomSanitizer,
    private service: ServiceService
  ) { }

  ngOnInit(): void {
    // this.service.findAll('coursestep').subscribe(data => {
    //   this.videoLink=this.sanitizer.bypassSecurityTrustResourceUrl(data.data[0].videoLink);;
    //   this.documentContent=data.data[0].content;
    //   this.quizExamPapers=data.data[0].quizExamPapers;
    //    //console.log(data.data[0].quizExamPapers)
    //    //console.log(data.data[0].videoLink)
    //    console.log(this.quizExamPapers)
    // });


    // Retrieve the selected course ID from localStorage
    const selectedCourseId = localStorage.getItem('selectedCourseId');

    // Fetch data from the backend
    this.service.findAll('coursestep').subscribe(data => {
      const courseData = data.data.find((course: any) => course.id.toString() === selectedCourseId);
      if (courseData) {
        this.videoLink = this.sanitizer.bypassSecurityTrustResourceUrl(courseData.videoLink);
        this.documentContent = courseData.content;
        this.quizExamPapers = courseData.quizExamPapers;
        this.quizExamPapers.questions.forEach((question: any) => {
          question.selectedOption = ''; // Default to an empty string
        });
      }
    });
    
    this.getExamScore();

  }



  // const youtubeUrl = data.data.videoLink;
  //     // this.videoLink = this.sanitizer.bypassSecurityTrustResourceUrl(data.data[0].videoLink);
  //     // this.documentContent = data.data.content;     
  //     // this.quizzes = data.data;


  

onOptionSelected(questionId: number, selectedOption: string) {
  const question = this.quizExamPapers.questions.find((q: any) => q.id === questionId);
  if (question) {
      question.selectedOption = selectedOption;
      //console.log(`Option selected for Question ${questionId}: ${selectedOption}`);
  }
}


  score: any | undefined;
  showAnswerSection: boolean = false;
  correctAnswers: any[] = [];

  showAnswers() {
    this.fetchCorrectAnswers();
}

//   calculateScore() {
//     if (this.quizExamPapers && this.quizExamPapers.questions) {
//         let correctCount = this.quizExamPapers.questions.filter((question: any) => question.selectedOption === question.correctOption).length;
//         console.log(correctCount);
//         this.score = (correctCount / this.quizExamPapers.questions.length) * 100;
//     }
// }

getExamScore(): any {
  if (this.quizExamPapers && this.quizExamPapers.questions) {
    let correctCount = this.quizExamPapers.questions.filter((question: any) => {
      const selected = question.selectedOption ? question.selectedOption.trim().toLowerCase() : '';
      const correct = question.correctOption ? question.correctOption.trim().toLowerCase() : '';
      return selected === correct;
    }).length;
    this.score = Math.round((correctCount / this.quizExamPapers.questions.length) * 100);
  } else {
    console.log('Quiz data not available or questions missing correctOption field.');
  }
}





fetchCorrectAnswers() {
  this.service.findAll('quiz').subscribe((data) => {
    const relevantAnswers = data.data.filter((question: any) =>
      this.quizExamPapers.questions.some((q: any) => q.id === question.id)
    );

    this.correctAnswers = relevantAnswers.map((question: any) => {
      const selectedOption = this.quizExamPapers.questions.find((q: any) => q.id === question.id)?.selectedOption || '';
      const correctOption = question.answer;
      return {
        id: question.id,
        question: question.question,
        selectedOption: selectedOption,
        correctOption: correctOption
      };
    });

    
    this.quizExamPapers.questions.forEach((question: any) => {
      const correctAnswer = this.correctAnswers.find((q: any) => q.id === question.id);
      if (correctAnswer) {
        question.correctOption = correctAnswer.correctOption;
      }
    });
    
    console.log(this.correctAnswers);
    this.showAnswerSection = true;
    this.getExamScore(); 
  });
}


submitAnswers() {
  console.log("shawon")
  this.getExamScore(); 
  this.showAnswerSection = false;
  console.log(`Your Score: ${this.score}%`);
  this.quizExamPapers.questions.forEach((question: any, index: number) => {
    //console.log(`Question ${index + 1}: Selected Option - ${question.selectedOption}`);
  });
}


 

}
