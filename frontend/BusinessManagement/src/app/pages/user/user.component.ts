import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../shared/service/auth.service";
import {UserService} from "../../shared/service/user.service";
import {Role, User} from "../../shared/model/User";
import {Router} from "@angular/router";


@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit{
  users: Array<User> = new Array<User>();
  currentUser!:User;
  constructor(private auth:AuthService, private userService:UserService, private router:Router) {
  }

  ngOnInit(): void {
    this.auth.getCurrentUser().subscribe(user =>{
      this.currentUser = user as User;
    });

    this.userService.getAllEmployess().subscribe(users =>{
      for(let user of (users as Array<User>)){
        this.users.push(user);
      }
    })
  }

  promote(user:User){
    if(user.role === 3) return;
    if(user.role === this.currentUser.role) return;

    this.userService.changeRole(user.email,user.role + 1).subscribe(res=>{
      window.location.reload();
    });
  }

  demote(user: User) {
    if(user.role === 0) return;
    if(user.role >= this.currentUser.role) return;
    this.userService.changeRole(user.email,user.role - 1).subscribe(res =>{
      window.location.reload();
    });
  }
  goToRegister() {
    this.router.navigateByUrl("/register");
  }

  delete(user: User) {
    this.userService.delete(user).subscribe();
    window.location.reload();
  }

  protected readonly Role = Role;
  protected readonly Object = Object;
}
