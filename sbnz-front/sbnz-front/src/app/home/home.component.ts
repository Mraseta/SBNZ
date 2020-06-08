import { Component, OnInit } from '@angular/core';
import { SearchService } from '../services/search.service';

import { NgbDate, NgbCalendar, NgbDateParserFormatter } from '@ng-bootstrap/ng-bootstrap';
import { NgForm } from '@angular/forms';
import { ReserveService } from '../services/reserve.service';

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
  accommodations : any;
  accommodationsLoaded = false;
  reserveWanted = false;
  accommodationId : any;
  accommodationPrice : any;

  hoveredDate: NgbDate;

  fromDate: NgbDate;
  toDate: NgbDate;


  zones = [
    {id: 0, name: ""},
    {id: 1, name: "Center"},
    {id: 2, name: "Normal"},
    {id: 3, name: "Suburbs"}
  ];

  constructor(private searchService: SearchService,
    private reserveService : ReserveService,
    private calendar: NgbCalendar,
    public formatter: NgbDateParserFormatter) { }

  ngOnInit() {
    this.fromDate = this.calendar.getToday();
    this.toDate = this.calendar.getNext(this.calendar.getToday(), 'd', 3);
    this.id = JSON.parse(localStorage.getItem('loggedUser')).id;
  }

  search() {
    this.searchService.search(this.country, this.city, this.zone.id, this.pricePerDayMin, this.pricePerDayMax)
    .subscribe(
      (data: any) => {
          this.accommodations = data;
          this.accommodationsLoaded = true;
      }, (error) => alert(error)
    );
  }
  reserve(id : any,price : any) {
    this.reserveWanted = true;
    this.accommodationId = id;
    this.accommodationPrice = price;

  }
  onSubmit(form: NgForm) {
    let start = this.fromDate.year + "-" + this.fromDate.month + "-" + this.fromDate.day;
    let end = this.toDate.year + "-" + this.toDate.month + "-" + this.toDate.day;
    console.log(this.fromDate.year + "-" + this.fromDate.month + "-" + this.fromDate.day);
    this.reserveService.reserve(this.id,this.accommodationId,this.accommodationPrice,start,end).subscribe(
      (data : any) => {
        console.log(data);
      }, (error) => {
        console.log(error);
      }
    )
    
    
  }

  onDateSelection(date: NgbDate) {
    if (!this.fromDate && !this.toDate) {
      this.fromDate = date;
    } else if (this.fromDate && !this.toDate && date.after(this.fromDate)) {
      this.toDate = date;
    } else {
      this.toDate = null;
      this.fromDate = date;
    }
  }

  isHovered(date: NgbDate) {
    return this.fromDate && !this.toDate && this.hoveredDate && date.after(this.fromDate) && date.before(this.hoveredDate);
  }

  isInside(date: NgbDate) {
    return date.after(this.fromDate) && date.before(this.toDate);
  }

  isRange(date: NgbDate) {
    return date.equals(this.fromDate) || date.equals(this.toDate) || this.isInside(date) || this.isHovered(date);
  }

  validateInput(currentValue: NgbDate, input: string): NgbDate {
    const parsed = this.formatter.parse(input);
    return parsed && this.calendar.isValid(NgbDate.from(parsed)) ? NgbDate.from(parsed) : currentValue;
  }

}
