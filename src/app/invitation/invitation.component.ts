import { Component, OnInit } from '@angular/core';
import {TypeBadgeService} from '../service/type-badge/type-badge.service';
import validate = WebAssembly.validate;
import {PointageService} from '../service/pointage/pointage.service';
import {TicketImprimerService} from '../service/ticket-imprimer/ticket-imprimer.service';
import {InvitationService} from '../service/invitation/invitation.service';
import {generate} from 'rxjs';
import {KeycloakService} from 'keycloak-angular';
import {ToastrService} from 'ngx-toastr';
import * as XLSX from'xlsx';


@Component({
  selector: 'app-test',
  templateUrl: './invitation.component.html',
  styleUrls: ['./invitation.component.css']
})
export class InvitationComponent implements OnInit {

  public invitation:any;
  public size:number=20;
  public currentPage:number=0;
  public totalPages:number;
  public pages:Array<number>;
  private currentKeyword: string="";
  private fileName:string= 'ExcelSheet.xlsx';
  constructor(private invitationService:InvitationService,private toastr: ToastrService) { }

  ngOnInit() {
   this.onGetTypeBadge();
  }

  onGetTypeBadge(){
    this.invitationService.getInvitation(this.currentPage,this.size)
      .subscribe(data=>{
       // console.log(data);

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
//generer
  onGenere(value: any) {
    if(value.titre == "" || value.nbr==""){
      this.showNotification("top", "center",3,"0");
    }else{
      this.invitationService.checkInvitation(value.titre)
        .subscribe(data=>{
          if(data==false){
            let elem: HTMLElement = document.getElementById('loading');
            elem.setAttribute("style","display:flex");
            this.invitationService.genrerInvitation(value.titre,value.nbr)
              .subscribe(data=>{
                console.log(data);
                this.showNotification("top", "center",1,data)
                elem.setAttribute("style","display:none");
                this.onGetTypeBadge();
              },err => {
                console.log(err)
              })
          }else {
            this.showNotification("top", "center",2,"0");
          }
        },err => {
          console.log(err)
        })
    }
  }

  //notification
  showNotification(from, align,color,nbr){

    switch(color){
      case 1:
        this.toastr.success('<span class="now-ui-icons ui-1_bell-53"></span> '+nbr+' invitations êtes enregistrer avec succès.', '', {
          timeOut: 8000,
          closeButton: true,
          enableHtml: true,
          toastClass: "alert alert-success alert-with-icon",
          positionClass: 'toast-' + from + '-' +  align
        });
        break;
      case 2:
        this.toastr.success('<span class="now-ui-icons ui-1_bell-53"></span> '+nbr+' invitations êtes enregistrer, Titre déja exist .', '', {
          timeOut: 8000,
          closeButton: true,
          enableHtml: true,
          toastClass: "alert alert-danger alert-with-icon",
          positionClass: 'toast-' + from + '-' +  align
        });
        break;
      case 3:
        this.toastr.success('<span class="now-ui-icons ui-1_bell-53"></span> '+nbr+' invitations êtes enregistrer, veuillez remplir tous les champs .', '', {
          timeOut: 8000,
          closeButton: true,
          enableHtml: true,
          toastClass: "alert alert-danger alert-with-icon",
          positionClass: 'toast-' + from + '-' +  align
        });
        break;
      default:
        break;
    }
  }

  exportexcel(): void
  {
    /* pass here the table id */
    let element = document.getElementById('excel-table');
    console.log(element)
    const ws: XLSX.WorkSheet =XLSX.utils.table_to_sheet(element);
    //const ws: XLSX.WorkSheet =XLSX.utils.table_to_sheet(this.invitation);
    console.log(ws);
    /* generate workbook and add the worksheet */
    const wb: XLSX.WorkBook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');

    /* save to file */
    XLSX.writeFile(wb, this.fileName);

  }
}

