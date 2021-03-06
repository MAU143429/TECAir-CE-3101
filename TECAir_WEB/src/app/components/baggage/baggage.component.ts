import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Baggage } from 'src/app/model/baggage';
import { Seat } from 'src/app/model/seat';
import { CheckIn } from 'src/app/model/check-in';
import { CheckData } from 'src/app/interface/check-data';
import { BaggageService } from 'src/app/service/baggage.service';
import { ConnectionService } from 'src/app/service/connection-service';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
@Component({
  selector: 'app-baggage',
  templateUrl: './baggage.component.html',
  styleUrls: ['./baggage.component.css']
})
export class BaggageComponent implements OnInit {

  newBaggage:Baggage = new Baggage
  newSeat:Seat = new Seat
  newCheckin:CheckIn = new CheckIn
  checkdata: CheckData[] | undefined; 
  closeResult = '';


  constructor(private modalService: NgbModal,private service:BaggageService,private connectionService:ConnectionService, private router:Router) { }
 
  ngOnInit(): void {}

  /**
   *  Este metodo permite hacer el display de un template en este caso el POP UP
   * @param content el template a mostrar
   */
   open(content:any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }
  
  /**
   * Metodo que permite crear las acciones del boton exit del popup
   * @param reason 
   * @returns 
   */
  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }

  /**
   * Metodo que permite crear una nueva maleta
   * @param newBaggage Objeto que contiene toda la informacion para registrar una maleta
   */
  addNewBaggage(newBaggage:Baggage){
    this.service.addBaggage(newBaggage).subscribe(baggage=> console.log(baggage));
  }

  addSeat(data:any){
    this.newSeat.no_vuelo = data.no_vuelo
    this.newSeat.no_transaccion = data.no_transaccion
    this.service.newSeat(this.newSeat).subscribe(seat=> console.log(seat));
  }

  /**
   * Este metodo trae la informacion de un pasajero que va a ser chequeado
   * @param data no_transaccion del pasajero a buscar
   */
  addNewCheckIn(data:any){
    this.service.getCheckData(data.no_transaccion).subscribe(checkin=> (this.checkdata = checkin));
    
  }

  /**
   * Este metodo permite confirmar el chequeo de un pasajero
   * @param data informacion del numero de tiquete al que se le va a confirmar el chequep
   */
  newCheckIn(data:any){
  this.newCheckin.no_transaccion = data.no_transaccion
  this.service.putCheckIn(this.newCheckin).subscribe(checkin=> console.log(checkin));
    
  }

  /** 
   * Este metodo permite dar el numero de transaccion para devolver un tiquete
   * @param data datos del tiquete
   */
   TicketDetails(data:any){
    this.connectionService.sendClickEvent2(parseInt(data.no_vuelo),parseInt(data.no_transaccion));
  }

}
