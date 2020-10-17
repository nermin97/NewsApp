import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import {AuthServiceService} from '../service/auth-service.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthServiceService, private router: Router) {}

  canActivate() {
    if (this.authService.loggedIn()) {
      return true;
    } else {
      this.router.navigate(['/news']);
      return false;
    }
  }
}
