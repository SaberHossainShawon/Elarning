import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { ServiceService } from '../../service/service.service';

@Component({
  selector: 'app-quizlist',
  standalone: true,
  imports: [HttpClientModule, CommonModule],
  templateUrl: './quizlist.component.html',
  styleUrl: './quizlist.component.scss',
  providers: [ServiceService],
})
export class QuizlistComponent {
  quizzes: any[] = [];
  
  constructor(private service: ServiceService) {}

  ngOnInit(): void {
    this.service.findAll('quiz').subscribe((data) => {
      this.quizzes = data.data;     
      console.log(data.data);
    });
  }
}
