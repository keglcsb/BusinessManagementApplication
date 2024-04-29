import {Issue} from "./Issue";

export enum Role{
  BASE,
  MANAGER,
  MODERATOR,
  OWNER
}

export interface User{
  id:number;
  email:string;
  firstName:string;
  lastName:string;
  salary:number;
  role: Role;
  issues:Set<Issue>;
}
