import {Component, EventEmitter, Output} from '@angular/core';
import {Router} from "@angular/router";
import {FormControl, Validators} from "@angular/forms";
import {AuthService} from "../../shared/service/auth.service";
import {LoginRequest} from "../../shared/dto/loginrequest";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email = new FormControl("",Validators.required);
  password = new FormControl("",Validators.required);
  @Output() loggedIn: EventEmitter<boolean> = new EventEmitter<boolean>();

  constructor(private router:Router, private auth:AuthService) {
  }
  login() {
    // if(this.email.value.length === 0 || this.password.value.length === 0) return;
    if(this.email.valid && this.password.valid){
      let loginRequest:LoginRequest = {
        email:this.email.value!,
        password:this.password.value!
      }
      this.auth.login(loginRequest).subscribe( (res: any) => {
        localStorage.setItem("auth", res.token);
        this.loggedIn.emit(true);
        this.router.navigateByUrl("/home");
      })
    }
  }
}
