import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {LoginRequest} from "../dto/loginrequest";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http:HttpClient) { }

  login(loginRequest:LoginRequest){
    return this.http.post('http://localhost:8080/login/',loginRequest);
  }

  getCurrentUser(){
    return this.http.get('http://localhost:8080/employee/user');
  }
}
