import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainComponent } from './main/main.component';
import { RouterLinkActive, RouterModule, Routes } from '@angular/router';

import { AboutusComponent } from '../aboutus/aboutus.component';
import { CourseComponent } from '../course/course.component';
import { HomeComponent } from '../home/home.component';

const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    children: [
      {
        path: '',
        component: HomeComponent
      },
      {
        path:'about',
        component:AboutusComponent
      },
      {
        path:'course',
        component:CourseComponent
      }
    ]
  }
]


@NgModule({
  declarations: [
    MainComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    RouterLinkActive
  ]
})
export class DashboardModule { }
