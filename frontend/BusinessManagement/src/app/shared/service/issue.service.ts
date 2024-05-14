import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CreateIssueRequest} from "../dto/createissuerequest";

@Injectable({
  providedIn: 'root'
})
export class IssueService {

  constructor(private http:HttpClient) { }

  getAll(){
    return this.http.get("http://localhost:8080/issues/");
  }

  getById(id:number){
    return this.http.post("http://localhost:8080/issues/", {id:id});
  }

  getByUser(id:number){
    return this.http.post("http://localhost:8080/issues/employee",{id:id});
  }

  assign(issueId: number, userId:number) {
    return this.http.put("http://localhost:8080/issues/assign",{issueId:issueId, employeeId:userId});
  }

  assignGroup(issueId: number, groupId: number) {
    return this.http.put("http://localhost:8080/issues/assignGroup", {issueId: issueId, groupId:groupId});
  }

  remove(issueId: number, userId:number){
    return this.http.put("http://localhost:8080/issues/remove", {issueId:issueId, employeeId:userId});
  }

  approve(issueId: number){
    return this.http.put("http://localhost:8080/issues/approve", {id:issueId});
  }

  add(issue: CreateIssueRequest) {
    return this.http.post("http://localhost:8080/issues/add", issue);
  }

  changeStatus(id: number) {
    return this.http.put("http://localhost:8080/issues/changeStatus", {id:id});
  }
}
