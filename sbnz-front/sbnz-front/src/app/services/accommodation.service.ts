import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccommodationService {

  constructor(private http: HttpClient) { }

  create(ownerId : any,
    name : any,
    country : any,
    city : any,
    address : any,
    distance : any,
    pricePerDay : any) {
    return this.http.post("http://localhost:8080/accommodation/one",{
      ownerId : ownerId,
      name : name,
      country : country,
      city : city,
      address : address,
      distance : distance,
      pricePerDay : pricePerDay
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
