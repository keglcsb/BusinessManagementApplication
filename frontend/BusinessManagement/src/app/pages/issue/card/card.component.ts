import {Component, Input, OnInit} from '@angular/core';
import {Issue, Status} from "../../../shared/model/Issue";

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {
  @Input('issue')
  issue!: Issue;
  constructor() {
    // this.issue = {
    //   name: "default",
    //   description: "default",
    //   id: 0
    // }
  }
  cardClick():void {
    console.log(this.issue.id + " is clicked!");
  }

  ngOnInit(): void {
    //console.log(this.issue);
  }

  protected readonly Status = Status;
}
