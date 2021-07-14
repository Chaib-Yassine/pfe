import {Component, Injectable, OnInit} from '@angular/core';

import validate = WebAssembly.validate;

import {DatePipe} from '@angular/common';
import {BadgeService} from '../service/badge/badge.service';
import {TypeBadgeService} from '../service/type-badge/type-badge.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Globals} from '../utility/globals';
import {TicketImprimerService} from '../service/ticket-imprimer/ticket-imprimer.service';



@Component({
  selector: 'app-test',
  templateUrl: './ticketMultiImpression.component.html',
  styleUrls: ['./ticketMultiImpression.component.css']
})
export class TicketMultiImpressionComponent implements OnInit {

  public badge:any;
  public typeBadge:any;
  public size:number=10;
  public currentPage:number=0;
  public totalPages:number;

  public pages:Array<number >;

  public totalSomme:number =0;
  public date: Date = new Date();
  public myAngularxQrCode: String  = "null43423432";
  public codeBare: number ;
  public datae:any;

  constructor(private ticketImprimerService:TicketImprimerService,private router:Router,private activatedRoute:ActivatedRoute) {


  }

  ngOnInit() {
   // console.log(this.activatedRoute.snapshot.params.id);
    this.onGetVente();

  }

  onGetVente(){
   // this.badgeService.getBadgeId(this.activatedRoute.snapshot.params.id)
    this.ticketImprimerService.getTicketImprimerCherche(0,2000,this.activatedRoute.snapshot.params.id)
      .subscribe(data=>{
        this.badge=data;
        console.log(data);
      },err => {
        console.log(err)
      })

  }

}

