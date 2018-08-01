import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  constructor() { }

  title = 'AngularLearning';

  users = [
    {name: "Roman", age: 20, surname: "Medvedev", image: "https://microhealth.com/assets/images/illustrations/personal-user-illustration-@2x.png"},
    {name: "Andrey", age: 19, surname: "Skvorcov", image: "https://cmporanga.tudotransparente.com.br/assets/img/userchat.png"},
    {name: "Gena", age: 25, surname: "Petrov", image: "https://microhealth.com/assets/images/illustrations/professional-user-illustration-@2x.png"},
    {name: "Volodya", age: 34, surname: "Pterekeev", image: "https://www.shareicon.net/data/2016/09/01/822711_user_512x512.png"},
    {name: "Artem", age: 15, surname: "Veeleev", image: "https://ibm-blue-box-help.github.io/help-documentation/img/en/avatar.png"},
    {name: "Lera", age: 26, surname: "Rogatko", image: "http://www.minikdehalaranaokulu.com/wp-content/uploads/2016/05/circled_user_female1600.png"},
  ];

  ngOnInit() {
  }

}
