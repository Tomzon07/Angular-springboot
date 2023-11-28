import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddExamComponent } from './add-exam/add-exam.component';
import { AddQuestionComponent } from './add-question/add-question.component';
import { AddUserProfileComponent } from './add-user-profile/add-user-profile.component';
import { ChartComponent } from './chart/chart.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { EditExamComponent } from './edit-exam/edit-exam.component';
import { EditQuestionComponent } from './edit-question/edit-question.component';
import { EditUserProfileComponent } from './edit-user-profile/edit-user-profile.component';
import { AdminGuard } from './guards/admin.guard';
import { UserGuard } from './guards/user.guard';
import { HomeComponent } from './home/home.component';
import { InstructionComponent } from './instruction/instruction.component';
import { ListQuestionComponent } from './list-question/list-question.component';
import { ListUsersComponent } from './list-users/list-users.component';
import { ListexamComponent } from './listexam/listexam.component';
import { LoginComponent } from './login/login.component';
import { NavComponent } from './nav/nav.component';
import { RegistrationComponent } from './registration/registration.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { ResultComponent } from './result/result.component';
import { UserExamComponent } from './user-exam/user-exam.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { UserQuestionListComponent } from './user-question-list/user-question-list.component';
import { Userprofile1Component } from './userprofile1/userprofile1.component';
import { ViewAdminResultsComponent } from './view-admin-results/view-admin-results.component';
import { ViewExamComponent } from './view-exam/view-exam.component';
import { ViewUserResultsComponent } from './view-user-results/view-user-results.component';

const routes: Routes = [
  { path: 'login',component:LoginComponent},
  // { path: 'home', component: HomeComponent,canActivate:[UserGuard]},
  {path:'registration',component:RegistrationComponent},
  {path:'dashboard',component:DashboardComponent,canActivate:[AdminGuard]},
  {path:'listexams',component:ListexamComponent,canActivate:[AdminGuard]},
  {path:'addexam',component:AddExamComponent,canActivate:[AdminGuard]},
  {path:'viewexam',component:ViewExamComponent,canActivate:[AdminGuard]},
  {path:'editexam',component:EditExamComponent,canActivate:[AdminGuard]},
  {path:'listquestions',component:ListQuestionComponent},
  {path:'listusers',component:ListUsersComponent,canActivate:[AdminGuard]},
  {path:'addQuestion',component:AddQuestionComponent,canActivate:[AdminGuard]},
  {path:'editQuestion',component:EditQuestionComponent,canActivate:[AdminGuard]},
  {path:'home',component:UserExamComponent,canActivate:[UserGuard]},
  {path:'userQuestionList/:qId',component:UserQuestionListComponent,canActivate:[UserGuard]},
  {path:'instructions/:eid',component:InstructionComponent,canActivate:[UserGuard]},
  {path:'result',component:ResultComponent,canActivate:[UserGuard]},
  {path:'userProfile',component:UserProfileComponent,canActivate:[UserGuard]},
  {path:'adduserProfile',component:AddUserProfileComponent,canActivate:[UserGuard]},
  {path:'edituserProfile',component:EditUserProfileComponent,canActivate:[UserGuard]},  
  {path:'reset-password/:url',component:ResetPasswordComponent},
  {path:'navigation',component:NavComponent},
  {path:'listResults',component:ViewUserResultsComponent,canActivate:[UserGuard]},
  {path:'listAdminResults',component:ViewAdminResultsComponent,canActivate:[AdminGuard]},
  {path:'chart',component:ChartComponent},
  {path:'profile',component:Userprofile1Component},
  {path:'**',component:LoginComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
