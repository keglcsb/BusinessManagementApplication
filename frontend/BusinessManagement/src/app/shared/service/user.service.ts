import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Role, User} from "../model/User";
import {RegisterRequest} from "../dto/registerrequest";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }

  getEmployee(id:number){
    return this.http.post("http://localhost:8080/employee/view", {id:id});
  }

  getAllEmployess() {
    return this.http.get("http://localhost:8080/employee/");
  }

  add(registerRequest:RegisterRequest){
    return this.http.post("http://localhost:8080/employee/add", registerRequest);
  }

  changePassword(oldPassword:string, password:string){
    return this.http.put("http://localhost:8080/employee/", {oldPassword:oldPassword, password:password});
  }

  changeRole(email:string, role:Role) {
     return this.http.put("http://localhost:8080/employee/changeRole", {email:email, role:role});
  }

  changeSalary(email:string, salary:number){
    return this.http.put("http://localhost:8080/employee/changeSalary", {email:email, salary:salary} );
  }
  delete(user:User){
    return this.http.delete("http://localhost:8080/employee/", {body:user});
  }

  getStats(){
    return this.http.get("http://localhost:8080/employee/stats");
  }
}
