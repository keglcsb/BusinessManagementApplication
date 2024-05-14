import {User} from "./User";

export enum Status {
  OPEN,
  HOLD,
  CLOSED
}
export interface Issue{
  name: string;
  description: string;
  id: number;
  status:Status;
  value:number;
  issuedAt: number;
  approvedAt: number;
  creator:User;
  approver:User;
  workers:Array<User>;
}
