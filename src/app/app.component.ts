import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'manager';
  opened = true;

  ngOnInit() {
    const numberInput = document.querySelector('.number-input') as HTMLInputElement;
    numberInput.addEventListener('input', this.handleNumberInput);
  }

  formatNumber(number: number): string {
    if (isNaN(number)) {
      return number.toString();
    }

    const formattedNumber = number.toString().replace('.', ',');
    return formattedNumber;
  }

  handleNumberInput(event: Event): void {
    const input = event.target as HTMLInputElement;
    const number = parseFloat(input.value);

    if (!isNaN(number)) {
      const formattedNumber = this.formatNumber(number);
      input.value = formattedNumber;
    }
  }
}
