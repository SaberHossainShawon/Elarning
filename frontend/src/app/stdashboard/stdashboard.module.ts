import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatMenuModule } from '@angular/material/menu';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { RouterModule, Routes } from '@angular/router';
import { CourseformComponent } from '../courseform/courseform.component';
import { CourselistComponent } from '../dashboardpage/courselist/courselist.component';
import { HomedashboardComponent } from '../dashboardpage/homedashboard/homedashboard.component';
import { PaymentlistComponent } from '../dashboardpage/paymentlist/paymentlist.component';
import { ProfileComponent } from '../dashboardpage/profile/profile.component';
import { QuizlistComponent } from '../dashboardpage/quizlist/quizlist.component';
import { QuizsComponent } from '../dashboardpage/quizs/quizs.component';
import { LoginComponent } from '../login/login.component';
import { AppComponent } from './app/app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { SidenavComponent } from './sidenav/sidenav.component';
import { ExampaperComponent } from '../dashboardpage/exampaper/exampaper.component';
import { StudentexamComponent } from '../dashboardpage/studentexam/studentexam.component';
import { StudentexampaperComponent } from '../dashboardpage/studentexampaper/studentexampaper.component';

const routes: Routes = [
  {
    path: '',
    component: AppComponent,
    children: [
      {
        path: '',
        component: HomedashboardComponent
      },
      {
        path: 'profile',
        component: ProfileComponent
      },
      {
        path: 'dashboardcourse',
        component: CourseformComponent
      },
      {
        path: 'logout',
        component: LoginComponent
      },
      {
        path: 'courselist',
        component: CourselistComponent
      },
      {
        path: 'paymentlist',
        component: PaymentlistComponent
      },
      {
        path: 'quiz',
        component: QuizsComponent
      },
      {
        path: 'quizlist',
        component: QuizlistComponent
      },
      {
        path: 'exampaperform',
        component: ExampaperComponent

      },
      {
        path: 'coursestep',
        component: CourseformComponent
      },
      // {
      //   path:'studentexam',
      //   component:StudentexamComponent
      // }
      {
        path: 'studentexampaper',
        component: StudentexampaperComponent
      }


    ]
  }
];


@NgModule({
  declarations: [
    DashboardComponent,
    HeaderComponent,
    SidenavComponent,
    HomeComponent,
    AppComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    MatSidenavModule,
    MatToolbarModule,
    MatMenuModule,
    MatIconModule,
    MatDividerModule,
    MatListModule,
    RouterModule.forChild(routes),
  ]
})
export class StdashboardModule { }
