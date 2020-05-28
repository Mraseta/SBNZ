import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  login(username: string, password: string) {
    return this.http.post("http://localhost:8080/auth/login", {
      username: username,
      password: password
    })
      .pipe(
        map((res: any) => {
          const data = res;
          localStorage.setItem('loggedUser', JSON.stringify(data));
          return data;
        }),
        catchError((err: any) => {
          console.log(err)
          return throwError(err)
        })
      )
  }

  register(username: string, password: string, firstName: string, lastName: string) {
    return this.http.post("http://localhost:8080/auth/register", {
      username: username,
      password: password,
      firstName: firstName,
      lastName: lastName
    })
      .pipe(
        map((res: any) => {
          const data = res;
          return data;
        }),
        catchError((err: any) => {
          console.log(err)
          return throwError(err)
        })
      )
  }
}
