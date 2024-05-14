import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Role, User} from "../../shared/model/User";
import {UserService} from "../../shared/service/user.service";
import {AuthService} from "../../shared/service/auth.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Issue, Status} from "../../shared/model/Issue";
import {IssueService} from "../../shared/service/issue.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit{
  user!:User;
  currentUser!:User;
  issues:Array<Issue> = new Array<Issue>();
  salary:FormControl = new FormControl("",Validators.required);
  passwordChangeForm:FormGroup = new FormGroup({
    oldPassword: new FormControl("",Validators.required),
    password: new FormControl("", Validators.required),
    repassword: new FormControl("", Validators.required)
  })

  constructor(private route:ActivatedRoute, private userService:UserService, private auth:AuthService, private issueService:IssueService) {
  }
  ngOnInit(): void {
    this.auth.getCurrentUser().subscribe(user =>{
      this.currentUser= (user as User);
    });

    let id = this.route.snapshot.paramMap.get('id')! as unknown as number;
    this.userService.getEmployee(id).subscribe(user => {
      this.user = user as User;
      this.issueService.getByUser(this.user.id).subscribe(data => {
        this.issues = data as Array<Issue>;
      });
    });


  }

  changeSalary() {
    if(this.salary.valid)
    this.userService.changeSalary(this.user.email,this.salary.value).subscribe(res => {
      console.log(res);
    })
  }

  changePassword() {
    if(this.passwordChangeForm.valid && this.passwordChangeForm.controls["password"].value === this.passwordChangeForm.controls["repassword"].value){
      this.userService.changePassword(this.passwordChangeForm.controls["oldPassword"].value, this.passwordChangeForm.controls["password"].value)
        .subscribe(res=>{
        console.log(res);
        window.location.reload();
      });
    }
  }

  protected readonly Role = Role;
  protected readonly Object = Object;


  protected readonly Status = Status;
}
