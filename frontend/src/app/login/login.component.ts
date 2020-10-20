import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
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

  constructor(private authService: AuthServiceService,
              private router: Router,
              private changeDetector: ChangeDetectorRef) {
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

  loginProcess() {
    if(this.formGroup.valid) {
      console.log(this.formGroup.value);
      this.authService.login(this.formGroup.value).subscribe(result => {
        console.log(result);
        localStorage.setItem('token', result.token);
        localStorage.setItem('username', result.user.username);
        localStorage.setItem('userType', result.user.userType);
        this.router.navigate(['/dashboard']);
      })
    }
  }
}

