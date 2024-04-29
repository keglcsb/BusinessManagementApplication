import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {Router} from "@angular/router";
import {AuthService} from "../service/auth.service";
import {User} from "../model/User";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit{

  @Output() Logout: EventEmitter<boolean> = new EventEmitter<boolean>();
  activeIssue:boolean = false;
  activeProfile:boolean = false;
  activeArchive: boolean = false;
  activeUsers: boolean = false;
  activeGroups: boolean = false;

  currentUser!:User;
  constructor(private router:Router, private auth:AuthService) {
  }

  ngOnInit():void {
    this.auth.getCurrentUser().subscribe(data =>{
      this.currentUser = (data as User);
    })
  }
  logout(){
    localStorage.clear();
    this.router.navigateByUrl('/login');
    this.Logout.emit(false);
  }

  setAllInactive(){
    this.activeIssue = false;
    this.activeProfile = false;
    this.activeArchive = false;
    this.activeUsers = false;
    this.activeGroups = false;
  }
  goToProfile() {
    this.setAllInactive();
    this.activeProfile = true;
    this.router.navigate(["/profile", this.currentUser.id!]).then(()=>
      window.location.reload()
    );
  }


  goToIssues() {
    this.setAllInactive();
    this.activeIssue = true;
    this.router.navigateByUrl('/issue');
  }

  goToArchive(){
    this.setAllInactive();
    this.activeArchive = true;
    this.router.navigateByUrl('/archive');
  }

  goToUsers(){
    this.setAllInactive();
    this.activeUsers = true;
    this.router.navigateByUrl('/user');
  }

  goToGroup() {
    this.setAllInactive();
    this.activeGroups = true;
    this.router.navigateByUrl('/group');
  }
}
