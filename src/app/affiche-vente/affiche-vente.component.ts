import { Component, OnInit } from '@angular/core';
import {TypeBadgeService} from '../service/type-badge/type-badge.service';
import validate = WebAssembly.validate;
import {AfficheVenteService} from '../service/affiche-vente/affiche-vente.service';
import {HttpClientModule} from '@angular/common/http';
import {ToastrService} from 'ngx-toastr';


@Component({
  selector: 'app-test',
  templateUrl: './affiche-vente.component.html',
  styleUrls: ['./affiche-vente.component.css']
})
export class AfficheVenteComponent implements OnInit {

  public vente:any;
  public size:number=10;
  public currentPage:number=0;
  public totalPages:number;
  public pages:Array<number>;
  public date: Date = new Date();
  private dateStart: string=this.date.getFullYear()+"-"+("0" + (this.date.getMonth() + 1)).slice(-2)+"-"+("0" + (this.date.getDate() )).slice(-2);
  private dateEnd: string=this.date.getFullYear()+"-"+("0" + (this.date.getMonth() + 1)).slice(-2)+"-"+("0" + (this.date.getDate() + 1)).slice(-2);
  public venteDetail:any;

  constructor(private afficheVenteService:AfficheVenteService,private toastr: ToastrService) { }

  ngOnInit() {
   this.chercherVentes()
  }

  onGetVente(){
    this.afficheVenteService.getVente(this.currentPage,this.size)
      .subscribe(data=>{

        this.totalPages=data["page"].totalPages;
        this.pages=new Array<number>(this.totalPages);
        this.vente=data;
        //console.log(this.totalPages);
      },err => {
        console.log(err)
      })
  }


  onPageProduct(i: number) {
    this.currentPage=i;
    //this.onGetTypeBadge();
    this.chercherVentes();
  }
  onCherche(value: any){
    this.currentPage=0;
    this.dateStart=value.startDate;
    this.dateEnd=value.endDate;
    this.chercherVentes();
  }
  chercherVentes() {
    this.afficheVenteService.getVenteCherche(this.currentPage,this.size,this.dateStart,this.dateEnd)
      .subscribe(data=>{

        this.totalPages=data["page"].totalPages;
        this.pages=new Array<number>(this.totalPages);
        this.vente=data;
        //console.log(this.totalPages);
      },err => {
        console.log(err)
      })

  }

  onDeleteProduct(p: any) {
    let conf=confirm("Etes vous sûre?");
    if(conf){
      //:console.log(p);
      this.afficheVenteService.deleteVente(p._links.self.href)
        .subscribe(data=>{
         this.showNotification('top', 'center',4)
          this.chercherVentes();
        },err => {
          console.log(err);
        }

      );
    }
  }


  onSaveProduct(value: any) {
    console.log(value);
    this.afficheVenteService.saveVente(this.afficheVenteService.host+"/TYPE-BADGE-SERVICE/typeBadges",value)
      .subscribe(resp=>{
           console.log(resp)
    },err => {
      console.log(err);
    })

  }

  onAfficheDetail(p: any) {
    this.afficheVenteService.getVenteDetail(p)
      .subscribe(data=>{
        this.venteDetail=data;
        //console.log(this.totalPages);
      },err => {
        console.log(err)
      })
  }
  showNotification(from, align,color){

    switch(color){

      case 2:
        this.toastr.success('<span class="now-ui-icons ui-1_bell-53"></span> Type de ticket bien ajouter.', '', {
          timeOut: 8000,
          closeButton: true,
          enableHtml: true,
          toastClass: "alert alert-success alert-with-icon",
          positionClass: 'toast-' + from + '-' +  align
        });
        break;
      case 3:
        this.toastr.warning('<span class="now-ui-icons ui-1_bell-53"></span> Type de ticket bien modifier.', '', {
          timeOut: 8000,
          closeButton: true,
          enableHtml: true,
          toastClass: "alert alert-warning alert-with-icon",
          positionClass: 'toast-' + from + '-' +  align
        });
        break;
      case 4:
        this.toastr.error('<span class="now-ui-icons ui-1_bell-53"></span> Type de ticket bien supprimer.', '', {
          timeOut: 8000,
          enableHtml: true,
          closeButton: true,
          toastClass: "alert alert-danger alert-with-icon",
          positionClass: 'toast-' + from + '-' +  align
        });
        break;

      default:
        break;
    }
  }
}

