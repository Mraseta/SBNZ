import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';



@Injectable({
  providedIn: 'root'
})
export class ReserveService {

  constructor(private http: HttpClient) { }

  reserve(userId: any,
    accommodationId: any,
    pricePerDay: any,
    startDate: any,
    endDate: any) {
    return this.http.post("http://localhost:8080/reserve/one", {
      userId: userId,
      accommodationId: accommodationId,
      pricePerDay: pricePerDay,
      startDate: startDate,
      endDate: endDate
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

  getConfirmed(id: number) {
    return this.http.post("http://localhost:8080/reserve/get-confirmed", {
      id: id
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

  approveReservation(id: number) {
    return this.http.post("http://localhost:8080/reserve/approve", {
      id: id
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
