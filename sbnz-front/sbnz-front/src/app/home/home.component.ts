import { Component, OnInit } from '@angular/core';
import { SearchService } from '../services/search.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  country: string;
  city: string;
  zone: any;
  pricePerDayMin: number;
  pricePerDayMax: number;
  id: number;

  zones = [
    {id: 0, name: "Center"},
    {id: 1, name: "Normal"},
    {id: 2, name: "Suburbs"}
  ];

  constructor(private searchService: SearchService) { }

  ngOnInit() {
    this.id = JSON.parse(localStorage.getItem('loggedUser')).id;
  }

  search() {
    this.searchService.search(this.country, this.city, this.zone.id, this.pricePerDayMin, this.pricePerDayMax)
    .subscribe(
      (data: any) => {
        //console.log('Logged in!');
        //this.router.navigate(['/']);
      }, (error) => alert(error.text)
    );
  }

}
