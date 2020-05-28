import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  firstName = "";
  lastName = "";
  username = "";
  password = "";
  cpassword = "";

  constructor(private userService: UserService,
    private router: Router) { }

  ngOnInit() {
  }

  register() {
    if(this.password !== this.cpassword) {
      alert('Passwords do not match!');
    }
    else {
      this.userService.register(this.username, this.password, this.firstName, this.lastName)
      .subscribe(
        (data: any) => {
          console.log('Registered!');
          this.router.navigate(['/login']);
        }, (error) => alert(error.text)
      );
    }
  }

  back() {
    this.router.navigate(['/login']);
  }

}
