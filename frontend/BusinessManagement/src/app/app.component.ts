import {Component, OnDestroy} from '@angular/core';
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnDestroy{
  isLoggedIn: boolean;

  constructor(private title:Title) {
    this.title.setTitle("Business Management");
    let user = localStorage.getItem('auth');
    this.isLoggedIn = user !== null;
  }
  LoggedInChanged(value:boolean){
    this.isLoggedIn = value;
  }

  ngOnDestroy(): void {
    localStorage.clear();
  }
}
