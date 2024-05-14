import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {IssueService} from "../../shared/service/issue.service";
import {Router} from "@angular/router";
import {CreateIssueRequest} from "../../shared/dto/createissuerequest";

@Component({
  selector: 'app-add-issue',
  templateUrl: './add-issue.component.html',
  styleUrls: ['./add-issue.component.css']
})
export class AddIssueComponent {
  addIssue:FormGroup = new FormGroup({
    name: new FormControl("", Validators.required),
    value: new FormControl("", Validators.required),
    desc: new FormControl("", Validators.required)
    }
  )

  constructor(private issueService:IssueService, private router:Router) {
  }

  add() {
    if(!this.addIssue.valid) return;
    let issue:CreateIssueRequest = {
      name:this.addIssue.controls["name"].value,
      value:this.addIssue.controls["value"].value,
      description:this.addIssue.controls["desc"].value
    }
    this.issueService.add(issue).subscribe(resp =>{
      this.router.navigateByUrl('/archive');
    });
  }
}
