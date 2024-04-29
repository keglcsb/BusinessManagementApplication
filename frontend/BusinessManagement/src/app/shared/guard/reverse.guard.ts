import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ReverseGuard implements CanActivate {

  constructor(private router: Router) {}

  canActivate(): boolean {
    if (localStorage.getItem('username') === "") {
      return true;
    } else {
      this.router.navigateByUrl('/home');
      return false;
    }
  }
}
