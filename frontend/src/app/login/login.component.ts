import { HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { ServiceService } from '../service/service.service';
import { ResponseStatus } from '../response/respnse';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [RouterModule, ReactiveFormsModule, HttpClientModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
  providers: [
    ServiceService
  ]
})
export class LoginComponent {
  constructor(private fb: FormBuilder, private router: Router, private service: ServiceService) {

  }
  errorMessage: string = '';
  loginForm = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(2)]],
  })

  onSubmit() {
    if (this.loginForm.valid) {
      const log = this.loginForm.value
      this.service.login(log).subscribe((data) => {
        console.log(data)
        if (data.status === ResponseStatus.SUCCESS) {
          this.router.navigate(['/paymentform'])
        } else {
          this.errorMessage = data.message;
        }
      })
    }
  }

  get email() {
    return this.loginForm.get('email')
  }
  get password() {
    return this.loginForm.get('password')

  }
}
