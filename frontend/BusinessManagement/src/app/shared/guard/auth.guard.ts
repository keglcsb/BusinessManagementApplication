import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router) {}

  canActivate(): boolean {
    if (sessionStorage.getItem('auth') !== "" || sessionStorage.getItem("auth") === null) {
      return true;
    } else {
      this.router.navigateByUrl('/login');
      return false;
    }
  }
}
