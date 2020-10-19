import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthServiceService} from './auth/service/auth-service.service';
import {Router} from '@angular/router';
import {map} from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';
  intervalId;

  constructor(public authService: AuthServiceService) {
  }


  logout() {
    this.authService.logout();

  }
}
