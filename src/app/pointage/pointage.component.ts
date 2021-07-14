import { Component, OnInit } from '@angular/core';
import {TypeBadgeService} from '../service/type-badge/type-badge.service';
import validate = WebAssembly.validate;
import {PointageService} from '../service/pointage/pointage.service';


@Component({
  selector: 'app-test',
  templateUrl: './pointage.component.html',
  styleUrls: ['./pointage.component.css']
})
export class PointageComponent implements OnInit {

  public pointage:any;
  public size:number=10;
  public currentPage:number=0;
  public totalPages:number;
  public pages:Array<number>;
  private  codeBarre :String="";
  private  porte :String="";
  private  tourniquet :String="";
  private  typeTitreAcces :String="";
  private  autorization :String="";
  private dateStart: string="";
  private dateEnd: string="";

  constructor(private pointageService:PointageService) { }

  ngOnInit() {
   this.onGetTypeBadge()
  }

  onGetTypeBadge(){
    this.pointageService.getTypeBadges(this.currentPage,this.size)
      .subscribe(data=>{

        this.totalPages=data["page"].totalPages;
        this.pages=new Array<number>(this.totalPages);
        this.pointage=data;
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

    this.codeBarre=value.codeBarre;
    this.porte=value.porte;
    this.typeTitreAcces=value.typeBadge;
    this.tourniquet=value.tourniquet;
    this.autorization=value.autorisation;
    this.dateStart=value.startDate;
    this.dateEnd=value.endDate;
    this.chercherTitre();
  }
  chercherTitre() {

    this.pointageService.getTypeBadgesCherche(this.currentPage,this.size,this.codeBarre,this.porte,this.tourniquet,this.typeTitreAcces,this.autorization,this.dateStart,this.dateEnd)
      .subscribe(data=>{

        this.totalPages=data["page"].totalPages;
        this.pages=new Array<number>(this.totalPages);
        this.pointage=data;
        //console.log(this.totalPages);
      },err => {
        console.log(err)
      })

  }

}

