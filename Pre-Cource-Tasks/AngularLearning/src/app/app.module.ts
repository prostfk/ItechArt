import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {UserComponent} from "./user/user.component";
import {HomePageComponent} from './home-page/home-page.component';
import {SetupPageComponent} from './setup-page/setup-page.component';
import {RouterModule} from "@angular/router";
import {SearchPipe} from "./search.pipe";
import {FormsModule} from "@angular/forms";
import { RegistrationComponent } from './registration/registration.component';


const routes = [
  {path: '', component: HomePageComponent},
  {path: 'setup', component: SetupPageComponent},
  {path: 'registration', component: RegistrationComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    HomePageComponent,
    SetupPageComponent,
    SearchPipe,
    RegistrationComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
