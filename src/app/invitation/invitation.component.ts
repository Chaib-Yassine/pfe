import { Component, OnInit } from '@angular/core';
import {TypeBadgeService} from '../service/type-badge/type-badge.service';
import validate = WebAssembly.validate;
import {PointageService} from '../service/pointage/pointage.service';
import {TicketImprimerService} from '../service/ticket-imprimer/ticket-imprimer.service';
import {InvitationService} from '../service/invitation/invitation.service';


@Component({
  selector: 'app-test',
  templateUrl: './invitation.component.html',
  styleUrls: ['./invitation.component.css']
})
export class InvitationComponent implements OnInit {

  public invitation:any;
  public size:number=10;
  public currentPage:number=0;
  public totalPages:number;
  public pages:Array<number>;
  private currentKeyword: string="";

  constructor(private invitationService:InvitationService) { }

  ngOnInit() {
   this.onGetTypeBadge()
  }

  onGetTypeBadge(){
    this.invitationService.getInvitation(this.currentPage,this.size)
      .subscribe(data=>{
        console.log(data);

        this.totalPages=data["page"].totalPages;
        this.pages=new Array<number>(this.totalPages);
        this.invitation=data;
        //console.log(this.totalPages);
      },err => {
        console.log(err)
      })
  }


  onPageProduct(i: number) {
    this.currentPage=i;
    //this.onGetTypeBadge();
    this.chercherTitre();
  }
  onCherche(value: any){
    this.currentPage=0;
    this.currentKeyword=value.keyword;
    this.chercherTitre();
  }
  chercherTitre() {

    this.invitationService.getInvitationCherche(this.currentPage,this.size,this.currentKeyword)
      .subscribe(data=>{

        this.totalPages=data["page"].totalPages;
        this.pages=new Array<number>(this.totalPages);
        this.invitation=data;
        //console.log(this.totalPages);
      },err => {
        console.log(err)
      })

  }

}

