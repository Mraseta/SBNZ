import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RuleService {

  constructor(private http: HttpClient) { }

  newRule(silver: any, gold: any, platinum: any) {
    return this.http.post("http://localhost:8080/new-rule", {
      strings: [''+silver, "20", "SILVER", ''+gold, "15", "GOLD", ''+platinum, "10", "PLATINUM"]
    })
      .pipe(
        map((res: any) => {
          const data = res;
          console.log(data);
          return data;
        }),
        catchError((err: any) => {
          console.log(err)
          return throwError(err)
        })
      )
  }
}
