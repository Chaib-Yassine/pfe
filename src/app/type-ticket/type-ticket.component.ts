import { Component, OnInit } from '@angular/core';
import {TypeBadgeService} from '../service/type-badge/type-badge.service';
import validate = WebAssembly.validate;
import {TypeTicketService} from '../service/type-ticket/type-ticket.service';
import {HttpClientModule} from '@angular/common/http';
import {ToastrService} from 'ngx-toastr';
import {Router} from '@angular/router';
import {KeycloakService} from 'keycloak-angular';


@Component({
  selector: 'app-test',
  templateUrl: './type-ticket.component.html',
  styleUrls: ['./type-ticket.component.css']
})
export class TypeTicketComponent implements OnInit {

  public typeTicket:any;
  public size:number=10;
  public currentPage:number=0;
  public totalPages:number;
  public pages:Array<number>;
  private currentKeyword: string="";
  public etat :number=1;
  public typeTicketE:any;
  constructor(private typeTicketService:TypeTicketService,private toastr: ToastrService,protected router: Router, protected keycloakAngular: KeycloakService) { }

  ngOnInit() {

    this.onGetTypeBadge();

  }

  onGetTypeBadge(){
    this.typeTicketService.getTypeBadges(this.currentPage,this.size)
      .subscribe(data=>{
        //console.log(data);

        this.totalPages=data["page"].totalPages;
        this.pages=new Array<number>(this.totalPages);
        this.typeTicket=data;
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

    this.typeTicketService.getTypeBadgesCherche(this.currentPage,this.size,this.currentKeyword)
      .subscribe(data=>{

        this.totalPages=data["page"].totalPages;
        this.pages=new Array<number>(this.totalPages);
        this.typeTicket=data;
        //console.log(this.totalPages);
      },err => {
        console.log(err)
      })

  }

  onDeleteProduct(p: any) {
    let conf=confirm("Etes vous sûre?");
    if(conf){
      //:console.log(p);
      this.typeTicketService.deleteTypeBadges(p._links.self.href)
        .subscribe(data=>{
          this.showNotification('top', 'center',4);
          this.chercherTitre();
        },err => {
          console.log(err);
        }

      );
    }
  }


  onSaveProduct(value: any) {
   // console.log("ici");
    this.typeTicketService.saveTypeBadges("",value)
      .subscribe(resp=>{
        this.showNotification('top', 'center',2);
        this.onGetTypeBadge();
      },err => {
        console.log(err);
      })
    this.onGetTypeBadge();
    this.etat=1;

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

  onUpdateProduct(value: any) {
     console.log(value);
    this.typeTicketService.updateTypeBadges("",value)
      .subscribe(resp=>{
        this.showNotification('top', 'center',3);
        this.onGetTypeBadge();
      },err => {
        console.log(err);
      })
    this.onGetTypeBadge();
    this.etat=1;
  }

  onEditProduct(p: any) {
    this.typeTicketE=p;
    this.etat=2;
  }
}

