import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { RegistrationComponent } from './registration/registration.component';
import { ReactiveFormsModule } from '@angular/forms';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AddExamComponent } from './add-exam/add-exam.component';
import { EditExamComponent } from './edit-exam/edit-exam.component';
import { DeleteExamComponent } from './delete-exam/delete-exam.component';
import { ListexamComponent } from './listexam/listexam.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ViewExamComponent } from './view-exam/view-exam.component';
import { NavComponent } from './nav/nav.component';
import { AddQuestionComponent } from './add-question/add-question.component';
import { EditQuestionComponent } from './edit-question/edit-question.component';
import { ViewQuestionComponent } from './view-question/view-question.component';
import { DeleteQuestionComponent } from './delete-question/delete-question.component';
import { ListQuestionComponent } from './list-question/list-question.component';
import { ListUsersComponent } from './list-users/list-users.component';
import { ViewQuestionsComponent } from './view-questions/view-questions.component';
import { UserExamComponent } from './user-exam/user-exam.component';
import { UheaderComponent } from './uheader/uheader.component';
import { UserQuestionListComponent } from './user-question-list/user-question-list.component';
import { InstructionComponent } from './instruction/instruction.component';
import { MatCardModule} from '@angular/material/card';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner'; 
import {MatDividerModule} from '@angular/material/divider'; 
import {MatButtonModule} from '@angular/material/button'; 
import { FormsModule } from '@angular/forms';
import { ResultComponent } from './result/result.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { AddUserProfileComponent } from './add-user-profile/add-user-profile.component';
import { ViewUserProfileComponent } from './view-user-profile/view-user-profile.component';
import { EditUserProfileComponent } from './edit-user-profile/edit-user-profile.component';
import { DeleteUserProfileComponent } from './delete-user-profile/delete-user-profile.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import {MatIconModule} from '@angular/material/icon'; 
import { NgxUiLoaderModule } from 'ngx-ui-loader';
import { NgxUiLoaderHttpModule } from 'ngx-ui-loader';
import {FlexLayoutModule} from "@angular/flex-layout";
import {MatToolbarModule} from '@angular/material/toolbar'; 
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import { ViewUserResultsComponent } from './view-user-results/view-user-results.component';
import { ChartComponent } from './chart/chart.component';
import { ViewAdminResultsComponent } from './view-admin-results/view-admin-results.component';
import { NgChartsModule } from 'ng2-charts';
import { Userprofile1Component } from './userprofile1/userprofile1.component';
import { InfiniteScrollModule } from "ngx-infinite-scroll";


@NgModule({
  declarations: [
    AppComponent, 
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    HomeComponent,
    RegistrationComponent,
    DashboardComponent,
    AddExamComponent,
    EditExamComponent,
    DeleteExamComponent,
    ListexamComponent,
    ViewExamComponent,
    NavComponent,
    AddQuestionComponent,
    EditQuestionComponent,
    ViewQuestionComponent,
    DeleteQuestionComponent,
    ListQuestionComponent,
    ListUsersComponent,
    ViewQuestionsComponent,
    UserExamComponent,
    UheaderComponent,
    UserQuestionListComponent,
    InstructionComponent,
    ResultComponent,
    UserProfileComponent,
    AddUserProfileComponent,
    ViewUserProfileComponent,
    EditUserProfileComponent,
    DeleteUserProfileComponent,
    ResetPasswordComponent,
    ViewUserResultsComponent,
    ChartComponent,
    ViewAdminResultsComponent,
    Userprofile1Component,
   ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatProgressSpinnerModule,
    MatDividerModule,
    MatButtonModule,
    FormsModule,
    NgxPaginationModule,
    MatIconModule,
    NgxUiLoaderModule,
    NgxUiLoaderHttpModule.forRoot({
      showForeground:true,
    }),
    FlexLayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatButtonToggleModule,
    NgChartsModule,
    InfiniteScrollModule
  ],
  providers: [],
  bootstrap: [AppComponent]

})
export class AppModule { }
