import {Injectable, Injector} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {baseUrl} from '../../../environments/environment';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  private http;
  private router;

  constructor(private injector: Injector) {
    this.http = this.injector.get(HttpClient);
    this.router = this.injector.get(Router);
  }

  login(data): Observable<any> {
    return this.http.post(`${baseUrl}auth/login`, data);
  }

  register(data): Observable<any> {
    return this.http.post(`${baseUrl}auth/register`, data);
  }

  loggedIn() {
    return !!localStorage.getItem('token');
  }

  getToken() {
    return localStorage.getItem('token');
  }

  getUser() {
    return localStorage.getItem('username');
  }

  isSuperAdmin(): boolean {
    return (localStorage.getItem('userType') != null && localStorage.getItem('userType') === 'SuperAdmin');
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['/news']);
  }
}
