import { Component } from '@angular/core'

@Component({
    selector:'my-app',
    template: `<label>Input your name: </label>
                <input [(ngModel)]="name" placeholder="name">
                <h1>Welcome, {{name}}!!!</h1>`
})
export class AppComponent {

}