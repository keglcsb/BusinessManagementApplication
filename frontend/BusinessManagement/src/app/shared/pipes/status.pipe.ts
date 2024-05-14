import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'status'
})
export class StatusPipe implements PipeTransform {

  transform(value: number, ...args: unknown[]): string {
    switch (value){
      case 0: return "Nyitott";
      case 1: return "Jóváhagyásra vár";
      case 2: return "Lezárt";
      default: return value.toString();
    }
  }

}
