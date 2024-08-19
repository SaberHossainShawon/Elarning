import { Routes } from '@angular/router';

import { AboutusComponent } from './aboutus/aboutus.component';
import { ProfileComponent } from './dashboardpage/profile/profile.component';
import { HomedashboardComponent } from './dashboardpage/homedashboard/homedashboard.component';
import { LoginComponent } from './login/login.component';
import { RgformComponent } from './rgform/rgform.component';
import { FormComponent } from './paymentform/form.component';
import { SearchablepageComponent } from './dashboardpage/searchablepage/searchablepage.component';
import { ExampaperComponent } from './dashboardpage/exampaper/exampaper.component';
import { CourseComponent } from './dashboardpage/course/course.component';
import { CoursestepComponent } from './dashboardpage/coursestep/coursestep.component';
import { StudentexamComponent } from './dashboardpage/studentexam/studentexam.component';
import { StudentexampaperComponent } from './dashboardpage/studentexampaper/studentexampaper.component';




export const routes: Routes = [

    // {
    //     path: '',
    //    component: HomeComponent
    // },
    {
        path: '',
        loadChildren: () => import('./dashboard/dashboard.module').then(m => m.DashboardModule)
    },
    {
        path: 'aboutus',
        component: AboutusComponent
    },
    {
        path: 'dashboard',
        loadChildren: () => import('./stdashboard/stdashboard.module').then(m => m.StdashboardModule)
    },
    {
        path: 'profile',
        component: ProfileComponent
    },
    {
        path: 'homedashboard',
        component: HomedashboardComponent
    },
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: 'registerfrom',
        component: RgformComponent
    },
    {
        path: 'paymentform',
        component: FormComponent
    },
    {
        path: 'searchablepage',
        component: SearchablepageComponent
    },
    {
        path: 'exampaper',
        component: ExampaperComponent
    },
    {
        path: 'coursestep',
        component: CoursestepComponent
    },
    // {
    //     path:'studentexam',
    //     component:StudentexamComponent
    // },
    {
        path:'studentexampaper',
        component:StudentexampaperComponent
    }
    

];
