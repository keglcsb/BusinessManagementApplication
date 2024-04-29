import { NgModule } from '@angular/core';
import {HomeComponent} from "./pages/home/home.component";
import {RouterModule} from "@angular/router";
import {LoginComponent} from "./pages/login/login.component";
import {IssueComponent} from "./pages/issue/issue.component";
import {ProfileComponent} from "./pages/profile/profile.component";
import {UserComponent} from "./pages/user/user.component";
import {ArchiveComponent} from "./pages/archive/archive.component";
import {AuthGuard} from "./shared/guard/auth.guard";
import {ReverseGuard} from "./shared/guard/reverse.guard";
import {TaskComponent} from "./pages/task/task.component";
import {NotfoundComponent} from "./pages/notfound/notfound.component";
import {RegisterComponent} from "./pages/register/register.component";
import {RegisterGuard} from "./shared/guard/register.guard";
import {GroupComponent} from "./pages/group/group.component";
import {GroupsetupComponent} from "./pages/groupsetup/groupsetup.component";

const routes = [
  { path: '', component: LoginComponent},
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
  { path: 'login', component: LoginComponent
    // , canActivate: [ReverseGuard]
  },
  { path: 'issue', component: IssueComponent, canActivate: [AuthGuard]},
  { path: 'profile/:id', component: ProfileComponent, canActivate: [AuthGuard]},
  { path: 'tasks/:task', component: TaskComponent, canActivate: [AuthGuard]},
  { path: 'user', component: UserComponent, canActivate: [AuthGuard]},
  { path: 'archive', component: ArchiveComponent, canActivate: [AuthGuard]},
  { path: 'group', component: GroupComponent, canActivate: [AuthGuard]},
  { path: 'groupSettings/:group', component: GroupsetupComponent, canActivate: [AuthGuard]},
  { path: 'groupSettings', component: GroupsetupComponent, canActivate: [AuthGuard]},
  { path: 'register', component: RegisterComponent, canActivate: [RegisterGuard]},
  { path: '**', component: NotfoundComponent}
]

@NgModule({
  imports: [ RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
