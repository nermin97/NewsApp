import { Component } from '@angular/core';
import {AuthServiceService} from './auth/service/auth-service.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';

  constructor(public authService: AuthServiceService, private router: Router) {
  }


  logout() {
    this.authService.logout();
    this.router.navigate(['/news']);
  }
}
