import { Component, OnInit } from '@angular/core';
import {TypeBadgeService} from '../service/type-badge/type-badge.service';
import validate = WebAssembly.validate;
import {PointageService} from '../service/pointage/pointage.service';
import {TicketImprimerService} from '../service/ticket-imprimer/ticket-imprimer.service';


@Component({
  selector: 'app-test',
  templateUrl: './ticket-imprimer.component.html',
  styleUrls: ['./ticket-imprimer.component.css']
})
export class TicketImprimerComponent implements OnInit {

  public ticketImprimer:any;
  public size:number=10;
  public currentPage:number=0;
  public totalPages:number;
  public pages:Array<number>;
  private currentKeyword: string="";

  constructor(private ticketImprimerService:TicketImprimerService) { }

  ngOnInit() {
   this.onGetTypeBadge()
  }

  onGetTypeBadge(){
    this.ticketImprimerService.getTicketImprimer(this.currentPage,this.size)
      .subscribe(data=>{
        console.log(data);

        this.totalPages=data["totalPages"];
        this.pages=new Array<number>(this.totalPages);
        this.ticketImprimer=data;
        //console.log(this.totalPages);
      },err => {
        console.log(err)
      })
  }


  onPageProduct(i: number) {
    console.log(i);
    this.currentPage=i;
    //this.onGetTypeBadge();
    this.onGetTypeBadge();
  }
  onCherche(value: any){
    this.currentPage=0;
    this.currentKeyword=value.keyword;
    this.chercherTitre();
  }
  chercherTitre() {

   /* this.ticketImprimerService.getTicketImprimerCherche(this.currentPage,this.size,this.currentKeyword)
      .subscribe(data=>{

        this.totalPages=data["totalPages"];
        this.pages=new Array<number>(this.totalPages);
        this.ticketImprimer=data;
        //console.log(this.totalPages);
      },err => {
        console.log(err)
      })*/

  }

}

