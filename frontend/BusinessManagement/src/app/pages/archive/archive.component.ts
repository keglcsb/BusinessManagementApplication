import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-archive',
  templateUrl: './archive.component.html',
  styleUrls: ['./archive.component.css']
})
export class ArchiveComponent implements OnInit{
  issues: Array<any> = new Array<any>();

  ngOnInit():void {
    this.issues.push({id:1, name: "asd", date: new Date()});
    this.issues.push({id:2, name: "asd", date: new Date()});
    this.issues.push({id:3, name: "asd", date: new Date()});
  }
}
