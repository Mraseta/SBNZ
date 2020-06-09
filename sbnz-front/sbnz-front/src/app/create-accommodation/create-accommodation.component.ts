import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AccommodationService } from '../services/accommodation.service';


@Component({
  selector: 'app-create-accommodation',
  templateUrl: './create-accommodation.component.html',
  styleUrls: ['./create-accommodation.component.css']
})
export class CreateAccommodationComponent implements OnInit {

  name: any;
  country: any;
  city: any;
  address: any;
  pricePerDay: any;
  distanceFromCenter: any;
  id: any;



  constructor(private accommodationService: AccommodationService) { }

  ngOnInit() {
    this.id = JSON.parse(localStorage.getItem('loggedUser')).id;
  }

  onSubmit(form: NgForm) {
    this.accommodationService.create(this.id, this.name, this.country, this.city, this.address, this.distanceFromCenter, this.pricePerDay)
      .subscribe(
        (data: any) => {
          console.log(data);
        }, (error) => {
          console.log(error);
        }
      )
  }

}
