import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthServiceService} from '../auth/service/auth-service.service';
import {Router} from '@angular/router';
import {openSnackBar} from '../news-public/news-public.component';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  emailControl;
  passwordControl;

  formGroup: FormGroup;

  constructor(private authService: AuthServiceService,
              private router: Router,
              private snackBar: MatSnackBar) { }

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
      username: this.emailControl,
      password: this.passwordControl
    });
  }

  getErrorMessage(input: string) {
    if (input === 'username' && (this.emailControl.hasError('required') || this.emailControl.hasError('email'))) {
      return 'Invalid email address!';
    } else if (input === 'password' && this.passwordControl.hasError('required')) {
      return 'Invalid password!';
    }
  }

  registerProcess() {
    if (this.formGroup.valid) {
      console.log(this.formGroup.value);
      this.authService.register(this.formGroup.value)
        .subscribe(
          result => {
          console.log(result);
          openSnackBar(this.snackBar, 'Successful!', 'Registration');
          this.router.navigate(['/register']);
        },
        error => {
          openSnackBar(this.snackBar, 'Failed!!!', 'Registration');
        });
    }
  }
}
