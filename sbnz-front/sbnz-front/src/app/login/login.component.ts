import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username: string;
  password: string;

  constructor(private router: Router,
    private userService: UserService) { }

  ngOnInit() {
  }

  login() {
    this.userService.login(this.username, this.password)
    .subscribe(
      (data: any) => {
        console.log('Logged in!');
        this.router.navigate(['/']);
      }, (error) => alert(error.text)
    );
  }

  register() {
    this.router.navigate(['/register']);
  }

}
