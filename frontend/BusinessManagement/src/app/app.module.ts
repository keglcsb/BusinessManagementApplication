import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {RouterOutlet} from "@angular/router";
import { AppRoutingModule } from './app-routing.module';
import {NavbarModule} from "./shared/navbar/navbar.module";
import {HomeComponent} from "./pages/home/home.component";
import {IssueComponent} from "./pages/issue/issue.component";
import {CardComponent} from "./pages/issue/card/card.component";
import {LoginModule} from "./pages/login/login.module";
import { ProfileComponent } from './pages/profile/profile.component';
import { UserComponent } from './pages/user/user.component';
import { ArchiveComponent } from './pages/archive/archive.component';
import { TaskComponent } from './pages/task/task.component';
import { NotfoundComponent } from './pages/notfound/notfound.component';
import {HttpClientModule, HTTP_INTERCEPTORS} from "@angular/common/http";
import {JwtInterceptor} from "./shared/service/jwtinterceptor";
import { RegisterComponent } from './pages/register/register.component';
import {ReactiveFormsModule} from "@angular/forms";
import { GroupComponent } from './pages/group/group.component';
import { GroupsetupComponent } from './pages/groupsetup/groupsetup.component';
import { AddIssueComponent } from './pages/add-issue/add-issue.component';
import { DatePipe } from './shared/pipes/date.pipe';
import { StatusPipe } from './shared/pipes/status.pipe';
import { StatisticsComponent } from './pages/statistics/statistics.component';
import { ValuePipe } from './shared/pipes/value.pipe';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    IssueComponent,
    CardComponent,
    ProfileComponent,
    UserComponent,
    ArchiveComponent,
    TaskComponent,
    NotfoundComponent,
    RegisterComponent,
    GroupComponent,
    GroupsetupComponent,
    AddIssueComponent,
    DatePipe,
    StatusPipe,
    StatisticsComponent,
    ValuePipe
  ],
  imports: [
    BrowserModule,
    RouterOutlet,
    AppRoutingModule,
    NavbarModule,
    LoginModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [{
    provide:HTTP_INTERCEPTORS,
    useClass:JwtInterceptor,
    multi: true
  }],
  exports: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
