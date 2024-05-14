import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'value'
})
export class ValuePipe implements PipeTransform {

  transform(value: number, ...args: unknown[]): string {
    return value.toString().slice(0,4);
  }
}
