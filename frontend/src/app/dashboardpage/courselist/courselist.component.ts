import { Component } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { RouterModule } from '@angular/router';
import { ServiceService } from '../../service/service.service';
import { HttpClientModule } from '@angular/common/http';


@Component({
  selector: 'app-courselist',
  standalone: true,
  imports: [MatTableModule,
    MatButtonModule,
    MatToolbarModule,RouterModule,HttpClientModule],
  templateUrl: './courselist.component.html',
  styleUrl: './courselist.component.scss',
  providers: [
    ServiceService
  ]
})
export class CourselistComponent {
  constructor(private service: ServiceService) { }
   ELEMENT_DATA: any[] = [
    // { courseName: 'Angular Basics', traineeName: 'John Doe', courseDuration: '3 weeks' },
    // { courseName: 'Spring Boot Advanced', traineeName: 'Jane Smith', courseDuration: '4 weeks' }
  ];
  dataSource :any[]=[];
  displayedColumns: string[] = ['courseName', 'traineeName', 'courseDuration'];
   
  ngOnInit(): void {
    this.service.findAll("course").subscribe((data) => {
      this.dataSource = data.data
      console.log(data.data)
    })
    
  }

  

}
