import {CanActivate, Router} from '@angular/router';
import {Injectable} from "@angular/core";
import {AuthService} from "../service/auth.service";
import {Role, User} from "../model/User";

@Injectable({
  providedIn: 'root'
})
export class RegisterGuard implements CanActivate {

  constructor(private router: Router, private auth:AuthService) {}

  // @ts-ignore
  canActivate(): boolean {
    if (localStorage.getItem('auth') !== "" || localStorage.getItem("auth") === null) {
      let currentUser:User;
      this.auth.getCurrentUser().subscribe(user =>{
        currentUser = user as User
        if(currentUser!.role === Role.OWNER){
          return true;
        } else {
          this.router.navigateByUrl("/home");
          return false;
        }
      });
    } else {
      this.router.navigateByUrl('/login');
      return false;
    }
  }
}
