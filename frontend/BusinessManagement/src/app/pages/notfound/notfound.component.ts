import {Component, OnChanges, OnInit, SimpleChanges} from '@angular/core';

@Component({
  selector: 'app-notfound',
  templateUrl: './notfound.component.html',
  styleUrls: ['./notfound.component.css']
})
export class NotfoundComponent implements OnChanges, OnInit{
  isLoggedIn:boolean = false;

  ngOnChanges(changes: SimpleChanges): void {
    this.isLoggedIn = localStorage.getItem('username') !== "" || localStorage.getItem('username') !== null;
  }

  ngOnInit(): void {
    this.isLoggedIn = localStorage.getItem('username') !== "" || localStorage.getItem('username') !== null;
  }


}
