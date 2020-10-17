import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {baseUrl} from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  constructor(private http: HttpClient) {}

  login(data): Observable<any> {
    return this.http.post(`${baseUrl}auth/login`, data);
  }

  authorize(): Observable<any> {
    return this.http.get(`${baseUrl}auth/`);
  }

  loggedIn() {
    return !!localStorage.getItem('token');
  }

  getToken() {
    return localStorage.getItem('token');
  }

  getUser() {
    return 'administrator@gmail.com'
    return localStorage.getItem('email');
  }

  logout() {
    localStorage.clear();
  }
}
