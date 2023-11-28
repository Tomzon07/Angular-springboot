import { HttpClient, HttpParams } from '@angular/common/http';
import { Token } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {

  examId: any
  examStatus: any;
  constructor(private router: Router, private http: HttpClient) { }

  getAccessToken(): any {
    return localStorage.getItem('accessToken');
  }
  getHeader(): any {
    return {
      headers: {
        Authorization: 'Contacts ' + this.getAccessToken(),
      },

    };
  }

  login(body: any): Observable<any> {
    return this.http.post(environment.apiUrl + '/login', body);
  }
  register(body: any): Observable<any> {
    return this.http.post(environment.apiUrl + '/users', body);
  }

  addExams(requestBody: any): Observable<any> {
    console.log(requestBody);

    return this.http.post(
      environment.apiUrl + '/exam',
      requestBody,
      this.getHeader()
    );
  }

  addQuestions(id: any, data: any): Observable<any> {


    return this.http.post(
      environment.apiUrl + '/question/' + id, data,
      this.getHeader()
    )

  }


  addContacts(requestBody: any): Observable<any> {
    return this.http.post(
      environment.apiUrl + '/userprofile', requestBody, this.getHeader()
    )
  }

  viewExam(examId: any) {
    console.log(examId);

    return this.http.get(
      environment.apiUrl + '/exam/' + examId,
      this.getHeader()
    );
  }

  viewQuestion(questionId: any) {
    console.log(questionId);

    return this.http.get(
      environment.apiUrl + '/question/q/' + questionId,
      this.getHeader()
    );
  }

  // viewUserProfile(){
  //   return this.http.get(
  //     environment.apiUrl + '/userprofile/update/' ,this.getHeader()
  //   );
  // }

  editUserProfile(body: any) {
    return this.http.put(
      environment.apiUrl + '/userprofile/update', body, this.getHeader()
    );

  }

  editExam(examId: any, responseBody: any): Observable<any> {
    return this.http.put(
      environment.apiUrl + '/exam/' + examId,
      responseBody,
      this.getHeader()
    );
  }


  editQuestion(questionId: any, responseBody: any): Observable<any> {
    return this.http.put(
      environment.apiUrl + '/question/' + questionId,
      responseBody,
      this.getHeader()
    );
  }
  deleteExam(examId: any): Observable<any> {
    return this.http.delete(
      environment.apiUrl + '/exam/' + examId,
      this.getHeader()
    );
  }


  deleteQuestion(questionId: any): Observable<any> {
    return this.http.delete(
      environment.apiUrl + '/question/' + questionId,
      this.getHeader()
    );
  }






  deleteUser(userId: any): Observable<any> {
    return this.http.delete(
      environment.apiUrl + '/users/' + userId,
      this.getHeader()
    );
  }

  viewUsers(id: any) {
    return this.http.get(environment.apiUrl + '/users' + id, this.getHeader());

  }

  listexams(): Observable<any> {
    return this.http.get(environment.apiUrl + '/exam/', this.getHeader());
  }

  listusers(): Observable<any> {
    return this.http.get(environment.apiUrl + '/users', this.getHeader());
  }

  listQuestions(id: any): Observable<any> {
    return this.http.get(environment.apiUrl + '/question/' + id, this.getHeader());
  }


  userProfileList(): Observable<any> {
    return this.http.get(environment.apiUrl + '/userprofile', this.getHeader());
  }


  evalExam(examId: any, body: any) {
    console.log("begin");

    console.log(body);

    // console.log(this.examId);
    // this.examId;

    return this.http.post(environment.apiUrl + '/question/user/eval/' + examId, body, this.getHeader());
  }

  logout(): void {


    localStorage.removeItem('accessToken');
    localStorage.removeItem('refreshToken');
    console.log('Logged Out...');
    localStorage.clear();
  }


  sendResetPswrdLink(data: any) {
    return this.http.post(environment.apiUrl + "/users/generateToken", data);
  }


  resetPassword(passwordResetToken: any, data: any) {
    return this.http.post(environment.apiUrl + "/users/resetPassword/" + passwordResetToken, data)
  }



  uploadProfile(data: FormData) {
    return this.http.post(environment.apiUrl + "/userprofile/save", data, this.getHeader())
  }

  getProfile() {
    return this.http.get(environment.apiUrl + "/userprofile/profile", {
      headers: {
        Authorization: 'Contacts ' + this.getAccessToken(),
      }, responseType: 'blob'
    })
  }

  listResults(): Observable<any> {
    return this.http.get(environment.apiUrl + '/result', this.getHeader());
  }


  listAdminResults(): Observable<any> {
    return this.http.get(environment.apiUrl + '/result/user', this.getHeader());
  }


  resultDownload() {
    return this.http.get(environment.apiUrl + '/result/download', {
      responseType: 'blob',
      headers: {
        Authorization:
          'Contacts ' + localStorage.getItem('accessToken'),
      },
    });
  }

  searchOption(params: HttpParams) {
    return this.http.get(environment.apiUrl + '/result/search', { params: params, headers: { Authorization: 'Contacts ' + this.getAccessToken() } });
  }


  chartData(queryParams: HttpParams): Observable<any> {
    return this.http.get(environment.apiUrl + '/users/chart', { headers: { Authorization: 'Contacts ' + this.getAccessToken() }, params: queryParams });
  }

  getAttemptCount(queryParams: HttpParams): Observable<any> {
    return this.http.get(environment.apiUrl + '/result/chart', { headers: { Authorization: 'Contacts ' + this.getAccessToken() }, params: queryParams });
  }

  csvUpload(params: FormData) {
    return this.http.post(environment.apiUrl + '/users/upload', params, { headers: { Authorization: 'Contacts ' + this.getAccessToken() } });
  }
}
