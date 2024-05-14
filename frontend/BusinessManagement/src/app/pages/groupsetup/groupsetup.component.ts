import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {GroupService} from "../../shared/service/group.service";
import {Role, User} from "../../shared/model/User";
import {FormControl, Validators} from "@angular/forms";
import {Issue} from "../../shared/model/Issue";
import {Group} from "../../shared/model/Group";

@Component({
  selector: 'app-groupsetup',
  templateUrl: './groupsetup.component.html',
  styleUrls: ['./groupsetup.component.css']
})
export class GroupsetupComponent implements OnInit{
  assignableEmployees:Array<User> = new Array<User>();
  group!:Group;
  name:FormControl = new FormControl("", Validators.required);
  leader:FormControl = new FormControl("", Validators.required);
  leaderMod: FormControl = new FormControl("", Validators.required);
  assigned: FormControl = new FormControl("", Validators.required);
  id:string | null = null;
  disabled: boolean = false;
  constructor(private route:ActivatedRoute, private groupService:GroupService, private router:Router) {
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('group');
    this.assignableEmployees.push({
      email: "",
      firstName: "",
      id: 0,
      issues: new Set<Issue>(),
      lastName: "",
      role: Role.BASE,
      salary: 0
    })
    this.groupService.getUsersByGroup(null).subscribe(data=>{
      for(let entry of (data as Array<User>)){
        this.assignableEmployees.push(entry);
      }
    });
    if(this.id){
      this.groupService.getGroup(this.id).subscribe(data => {
        this.group = data as Group;
        this.name.setValue(this.group.name);
        this.disabled = true;
        this.leaderMod.setValue(this.group.leader.id);
      });
    }
  }

  addGroup() {
    if(this.name.valid && this.leader.valid){
      console.log(this.leader.value);
      this.groupService.addGroup({name:this.name.value, leaderId:this.leader.value}).subscribe(res=>{
        console.log(res);
        this.router.navigateByUrl("/group");
      });
    }
  }

  modify() {
    if(this.leaderMod.valid){
      this.groupService.changeLeader(this.group.id, this.leaderMod.value).subscribe(res => {
        console.log(res);
      });
    }
  }

  assign() {
    if(this.assigned.valid){
      this.groupService.assign(this.group.id, this.assigned.value).subscribe(res => console.log(res));
    }
  }

  remove(id: number) {
    this.groupService.remove(this.group.id, id).subscribe(res=> {
      console.log(res)
      window.location.reload();
    });
  }

  delete() {
    this.groupService.delete(this.group.id).subscribe(res =>{
      console.log(res);
      this.router.navigateByUrl('/group');
    })
  }
}
