import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {IssueService} from "../../shared/service/issue.service";
import {AuthService} from "../../shared/service/auth.service";
import {Issue, Status} from "../../shared/model/Issue";
import {Role, User} from "../../shared/model/User";
import {FormControl, Validators} from "@angular/forms";
import {UserService} from "../../shared/service/user.service";
import {Group} from "../../shared/model/Group";
import {GroupService} from "../../shared/service/group.service";

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit{
  TaskId:string = "";
  issue!:Issue;
  assigned:  FormControl = new FormControl("", Validators.required);
  assignableEmployees: Array<User> = new Array<User>();
  assignedGroup: FormControl = new FormControl("", Validators.required);
  assignableGroups: Array<Group> =new Array<Group>();
  currentUser!:User;
  constructor(private route:ActivatedRoute, private issueService:IssueService, private authService:AuthService, private userService:UserService, private groupService:GroupService) {
  }

  ngOnInit(): void {
    let id = this.route.snapshot.paramMap.get('task')! as unknown as number;
    this.assignableEmployees.push({
      email: "",
      firstName: "",
      id: 0,
      issues: new Set<Issue>(),
      lastName: "",
      role: Role.BASE,
      salary: 0
    });
    this.groupService.getAll().subscribe(groups =>{
      for(let group of (groups as Array<Group>)){
        this.assignableGroups.push(group);
      }
    });

    this.authService.getCurrentUser().subscribe(user => {
      this.currentUser = user as User;
    });

    this.issueService.getById(id).subscribe(issue => {
      this.issue = issue as Issue;
      this.userService.getAllEmployess().subscribe(users =>{
        for(let user of (users as Array<User>)){
          this.assignableEmployees.push(user);
          for(let worker of this.issue.workers){
            if(worker.id===user.id)this.assignableEmployees.pop();
          }
        }
      });
    });
  }
  assign() {
    if(!this.assigned.valid) return;
    this.issueService.assign(this.issue.id, this.assigned.value).subscribe(
      resp => {
        window.location.reload();
      }
    );
  }

  assignGroup() {
    if(!this.assignedGroup.valid) return;
    this.issueService.assignGroup(this.issue.id, this.assignedGroup.value).subscribe(
      resp => {
        window.location.reload();
      }
    )
  }

  approve() {
    this.issueService.approve(this.issue.id).subscribe(
      resp => {
        window.location.reload();
      }
    )
  }

  removeUser(id: number) {
    this.issueService.remove(this.issue.id, id).subscribe(
      resp => {
        window.location.reload();
      }
    );
  }

  changeStatus() {
    this.issueService.changeStatus(this.issue.id).subscribe( resp=>
      window.location.reload()
    );
  }

  protected readonly Role = Role;
  //protected readonly Object = Object;
}
