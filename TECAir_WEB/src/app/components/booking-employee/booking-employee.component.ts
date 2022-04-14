import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-booking-employee',
  templateUrl: './booking-employee.component.html',
  styleUrls: ['./booking-employee.component.css']
})
export class BookingEmployeeComponent implements OnInit {

  bookingdata = [
    {
      "no_vuelo" : "XMF-675",
      "origen" : "SJO Costa Rica Aeropuerto Internacional Juan Santamaria",
      "destino": "MXN Mexico Aeropuerto Benito Juarez",
      "fecha": "22/04/2022",
      "h_salida": "1:50 PM",
    },
    {
      "no_vuelo" : "MGFR-737",
      "origen" : "PAN Panama Aeropuerto Internacional de Tocumen",
      "destino": "LAX Estados Unidos Aeropuerto LAX",
      "fecha": "19/04/2022",
      "h_salida": "2:00 AM",
    }
]

  ticketsdata = [
    {
      "no_vuelo" : "XMF-675",
      "no_transaccion" : "#19834783",
      "fecha": "22/04/2022",
      "h_salida": "1:50 PM",
    },
    {
      "no_vuelo" : "MGFR-737",
      "no_transaccion" : "#24545767",
      "fecha": "19/04/2022",
      "h_salida": "2:00 AM",
    }
]

  constructor() { }

  ngOnInit(): void {
  }

}
