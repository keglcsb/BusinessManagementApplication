import {Component, OnChanges, OnInit, SimpleChanges} from '@angular/core';

@Component({
  selector: 'app-notfound',
  templateUrl: './notfound.component.html',
  styleUrls: ['./notfound.component.css']
})
export class NotfoundComponent implements OnChanges, OnInit{
  isLoggedIn:boolean = false;

  ngOnChanges(changes: SimpleChanges): void {
    this.isLoggedIn = sessionStorage.getItem('username') !== "" || sessionStorage.getItem('username') !== null;
  }

  ngOnInit(): void {
    this.isLoggedIn = sessionStorage.getItem('username') !== "" || sessionStorage.getItem('username') !== null;
  }


}
