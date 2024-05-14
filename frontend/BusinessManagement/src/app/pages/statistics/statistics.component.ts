import {Component, OnInit} from '@angular/core';
import {Role, User} from "../../shared/model/User";
import {UserService} from "../../shared/service/user.service";
import {UserStats} from "../../shared/dto/UserStats";

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent implements OnInit{
  users: Array<UserStats> = new Array<UserStats>();
  constructor(private userService:UserService) {
  }


  ngOnInit(): void {
    this.userService.getStats().subscribe(data =>{
      for(let item of (data as Array<UserStats>)){
        this.users.push(item);
      }
    });
  }


  protected readonly Role = Role;
  protected readonly Object = Object;
}
