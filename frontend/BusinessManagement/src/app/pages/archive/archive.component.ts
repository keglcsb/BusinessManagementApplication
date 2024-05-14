import {Component, OnInit} from '@angular/core';
import {IssueService} from "../../shared/service/issue.service";
import {Issue, Status} from "../../shared/model/Issue";
import {Role, User} from "../../shared/model/User";
import {AuthService} from "../../shared/service/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-archive',
  templateUrl: './archive.component.html',
  styleUrls: ['./archive.component.css']
})
export class ArchiveComponent implements OnInit{
  issues: Array<Issue> = new Array<Issue>();
  currentUser!:User;
  dataAvailable:boolean = false;
  constructor(private issueService:IssueService, private authService:AuthService, private router:Router) {
  }

  ngOnInit():void {
    this.authService.getCurrentUser().subscribe(user => {
      this.currentUser = user as User;
    })

    this.issueService.getAll().subscribe(data => {
      this.issues = data as Array<Issue>;
      this.dataAvailable = true;
    });
  }
  goToAdd() {
    this.router.navigateByUrl('/addIssue');
  }

  protected readonly Role = Role;
    protected readonly Object = Object;
  protected readonly Status = Status;


}
