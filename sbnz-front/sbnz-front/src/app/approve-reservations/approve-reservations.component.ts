import { Component, OnInit } from '@angular/core';
import { ReserveService } from '../services/reserve.service';

@Component({
  selector: 'app-approve-reservations',
  templateUrl: './approve-reservations.component.html',
  styleUrls: ['./approve-reservations.component.css']
})
export class ApproveReservationsComponent implements OnInit {
  confirmedRes = [];
  loggedUser: any;

  constructor(private reserveService: ReserveService) { }

  ngOnInit() {
    this.loggedUser = JSON.parse(localStorage.getItem('loggedUser'));
    this.getConfirmed();
  }

  getConfirmed() {
    this.reserveService.getConfirmed(this.loggedUser.id)
    .subscribe(
      (data: any) => {
        console.log(data);
        this.confirmedRes = Object.assign([], (data));
      }
    )
  }

  approveReservation(id: number) {
    this.reserveService.approveReservation(id)
    .subscribe(
      (data: any) => {
        console.log(data);
        this.getConfirmed();
      }
    )
  }

}
