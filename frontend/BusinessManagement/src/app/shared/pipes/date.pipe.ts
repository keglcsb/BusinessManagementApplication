import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'date'
})
export class DatePipe implements PipeTransform {

  transform(value: number, ...args: unknown[]): string {
    let date:Date = new Date(value);
    return date.getFullYear() + '.'
      + (date.getMonth() < 10 ? '0'+(date.getMonth()+1): (date.getMonth()+1)) + '.'
      + (date.getDay() < 10? '0' + date.getDay(): date.getDay());
  }

}
