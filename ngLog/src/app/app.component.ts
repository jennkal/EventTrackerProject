import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'ngLog';

  slideDirection: string = '';

  addSlideClass(): void {
    if (this.slideDirection === 'right') {
      this.slideDirection = 'slide-right';
    } else if (this.slideDirection === 'left') {
      this.slideDirection = 'slide-left';
    }
  }
}
