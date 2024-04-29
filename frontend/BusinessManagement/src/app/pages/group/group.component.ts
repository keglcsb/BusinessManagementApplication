import {Component, OnInit} from '@angular/core';
import {Group} from "../../shared/model/Group";
import {GroupService} from "../../shared/service/group.service";
import {group} from "@angular/animations";
import {Role} from "../../shared/model/User";
import {Router} from "@angular/router";

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.css']
})
export class GroupComponent implements OnInit{
  groups: Array<Group> = new Array<Group>();

  constructor(private groupService:GroupService, private router:Router) {
  }

  ngOnInit(): void {
    this.groupService.getAll().subscribe((res) => {
      this.groups = res as Array<Group>;
    })
  }
  goToSetup() {
    this.router.navigateByUrl("/groupSettings");
  }

}
