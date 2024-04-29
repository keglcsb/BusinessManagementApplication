import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Group} from "../model/Group";

@Injectable({
  providedIn: 'root'
})
export class GroupService {

  constructor(private http:HttpClient) { }

  getAll(){
    return this.http.get("http://localhost:8080/group/");
  }

  getGroup(id:string){
    return this.http.post("http://localhost:8080/group/", {id:id});
  }

  getUsersByGroup(id:any){
    return this.http.post("http://localhost:8080/employee/group", {id:id})
  }

  addGroup(group:any){
    return this.http.post("http://localhost:8080/group/add", group);
  }

  changeLeader(groupId:number, employeeId:number){
    return this.http.put("http://localhost:8080/group/changeLeader", {groupId:groupId, employeeId:employeeId});
  }

  assign(groupId:number, employeeId:number){
    return this.http.put("http://localhost:8080/group/assign", {groupId:groupId, employeeId:employeeId});
  }

  remove(groupId:number, employeeId:number) {
    return this.http.put("http://localhost:8080/group/remove", {groupId:groupId, employeeId:employeeId});
  }

  delete(groupId:number){
    return this.http.delete("http://localhost:8080/group/", {body:{id:groupId}});
  }
}
