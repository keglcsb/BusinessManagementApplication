import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit{
  TaskId:string = "";
  constructor(private route:ActivatedRoute) {
  }

  ngOnInit(): void {
    this.TaskId = this.route.snapshot.paramMap.get('task')!;
  }
}
