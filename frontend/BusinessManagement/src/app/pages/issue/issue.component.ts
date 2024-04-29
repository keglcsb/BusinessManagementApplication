import { Component } from '@angular/core';
import {Issue} from "../../shared/model/Issue";

@Component({
  selector: 'app-issue',
  templateUrl: './issue.component.html',
  styleUrls: ['./issue.component.css']
})
export class IssueComponent {
  issues:Array<Issue> = new Array<Issue>();
  constructor() {
    for(let i = 1; i < 15; i++){
      let temp:Issue  = {
        id: i,
        name: "asd" + i,
        description: "Illumine patris"
      }
      this.issues.push(temp);
    }
  }

}
