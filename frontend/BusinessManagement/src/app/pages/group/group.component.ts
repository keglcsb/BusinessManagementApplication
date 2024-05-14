import {Component, OnInit} from '@angular/core';
import {Group} from "../../shared/model/Group";
import {GroupService} from "../../shared/service/group.service";
import {Role, User} from "../../shared/model/User";
import {Router} from "@angular/router";
import {AuthService} from "../../shared/service/auth.service";

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.css']
})
export class GroupComponent implements OnInit{
  groups: Array<Group> = new Array<Group>();
  dataAvailable:boolean = false;
  constructor(private groupService:GroupService, private router:Router, private authService:AuthService) {
  }

  ngOnInit(): void {
    this.groupService.getAll().subscribe((res) => {
      this.groups = res as Array<Group>;
      this.dataAvailable = true;
    })
  }
  goToSetup() {
    this.router.navigateByUrl("/groupSettings");
  }

}
