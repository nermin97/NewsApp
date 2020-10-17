import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthServiceService} from '../auth/service/auth-service.service';
import {Router} from '@angular/router';
// import {FormControl, Validators} from '@angular/forms';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
  emailControl;
  passwordControl;

  formGroup: FormGroup;

  constructor(private authService: AuthServiceService, private router: Router) {
  }

  ngOnInit(): void {
    this.emailControl = new FormControl('', [
      Validators.email,
      Validators.required
    ]);
    this.passwordControl = new FormControl('', [
      Validators.required
    ]);
    this.initForm();
  }

  initForm() {
    this.formGroup = new FormGroup({
      email: this.emailControl,
      password: this.passwordControl
    });
  }

  getErrorMessage(input: string) {
    if (input === 'email' && (this.emailControl.hasError('required') || this.emailControl.hasError('email'))) {
      return 'Invalid email address!';
    } else if (input === 'password' && this.passwordControl.hasError('required')) {
      return 'Invalid password!';
    }
  }

  loginProcess() {
    if(this.formGroup.valid) {
      console.log(this.formGroup.value);
      this.authService.login(this.formGroup.value).subscribe(result => {
        if(result.success) {
          console.log(result);
          alert(result.message);
          localStorage.setItem('token', result.token);
          localStorage.setItem('email', result.email);
          this.router.navigate(['/dashboard']);
        } else {
          alert(result.message);
        }
      })
    }
  }
}

