import {Component, OnInit} from '@angular/core';
import {Issue, Status} from "../../shared/model/Issue";
import {User} from "../../shared/model/User";
import {IssueService} from "../../shared/service/issue.service";
import {AuthService} from "../../shared/service/auth.service";

@Component({
  selector: 'app-issue',
  templateUrl: './issue.component.html',
  styleUrls: ['./issue.component.css']
})
export class IssueComponent implements OnInit{
  issues:Array<Issue> = new Array<Issue>();
  currentUser!:User;
  constructor(private authService:AuthService, private issueService:IssueService) {
  }

  ngOnInit(): void {
    this.authService.getCurrentUser().subscribe(user => {
      this.currentUser = user as User;
      this.issueService.getByUser(this.currentUser.id).subscribe(data =>{
        for(let item of (data as Array<Issue>)){
          if(item.status != Status.CLOSED) this.issues.push(item);
        }
      });
    });
  }
}
