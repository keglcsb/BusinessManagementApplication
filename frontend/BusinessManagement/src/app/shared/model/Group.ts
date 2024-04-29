import {User} from "./User";

export interface Group{
  id:number,
  name:string,
  leader:User,
  members: Array<User>
}
