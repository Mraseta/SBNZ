import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private http: HttpClient) { }

  search(country: string, city: string, zone: number, pricePerDayMin: number, pricePerDayMax: number) {
    return this.http.get("http://localhost:8080/search/findAll?country="+country+"&city="+city+"&zone="+zone+"&pricePerDayMin="+pricePerDayMin+"&pricePerDayMax="+pricePerDayMax+"&id=" + JSON.parse(localStorage.getItem('loggedUser')).id)
      .pipe(
        map((res: any) => {
          const data = res;
          console.log(data);
          //localStorage.setItem('loggedUser', JSON.stringify(data));
          return data;
        }),
        catchError((err: any) => {
          console.log(err)
          return throwError(err)
        })
      )
  }
}
