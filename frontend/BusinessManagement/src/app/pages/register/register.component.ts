import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {RegisterRequest} from "../../shared/dto/registerrequest";
import {UserService} from "../../shared/service/user.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  registerForm:FormGroup = new FormGroup({
    email: new FormControl("", Validators.required),
    firstName: new FormControl("", Validators.required),
    lastName: new FormControl("", Validators.required),
    password: new FormControl("", Validators.required),
    repassword: new FormControl("", Validators.required),
    salary: new FormControl("", Validators.required)
    });

  constructor(private userService:UserService) {
  }

  register() {
    if(this.registerForm.valid && this.registerForm.controls["password"].value === this.registerForm.controls["repassword"].value){
      let registerRequest:RegisterRequest = {
        email:this.registerForm.controls["email"].value,
        firstName:this.registerForm.controls["firstName"].value,
        lastName:this.registerForm.controls["lastName"].value,
        password:this.registerForm.controls["password"].value,
        salary:this.registerForm.controls["salary"].value
      }
      this.userService.add(registerRequest).subscribe(res=>{
        history.back();
      });

    } else {
      console.log("invalid");
    }
  }

  cancel() {
    history.back();
  }
}
