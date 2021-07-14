import { Component, OnInit } from '@angular/core';

import validate = WebAssembly.validate;

import {DatePipe} from '@angular/common';
import {BadgeService} from '../service/badge/badge.service';
import {TypeBadgeService} from '../service/type-badge/type-badge.service';
import {ActivatedRoute, Router} from '@angular/router';
import {QRCodeModule} from 'angularx-qrcode';
import {NgxBarcodeModule} from 'ngx-barcode';



@Component({
  selector: 'app-test',
  templateUrl: './badgeImpression.component.html',
  styleUrls: ['./badgeImpression.component.css']
})
export class BadgeImpressionComponent implements OnInit {

  public badge:any;
  public typeBadge:any;
  public size:number=10;
  public currentPage:number=0;
  public totalPages:number;

  public pages:Array<number >;

  public totalSomme:number =0;
  public date: Date = new Date();
  public myAngularxQrCode: string = null;
  public codeBare: number ;


  constructor(private badgeService:BadgeService,private router:Router,private activatedRoute:ActivatedRoute,private qRCodeModule:QRCodeModule) {


  }

  ngOnInit() {
   // console.log(this.activatedRoute.snapshot.params.id);
    this.onGetVente();

  }

  onGetVente(){
    this.badgeService.getBadgeId(this.activatedRoute.snapshot.params.id)
      .subscribe(data=>{
        //console.log("ddddd"+data["id"]);
        this.myAngularxQrCode = data["codebare"];
        this.codeBare= data["codebare"];
        this.badge=data;
      },err => {
        console.log(err)
      })

  }

}

