import { Component, OnInit } from '@angular/core';
import {TypeBadgeService} from '../service/type-badge/type-badge.service';
import validate = WebAssembly.validate;
import {HttpClientModule} from '@angular/common/http';
import {ToastrService} from 'ngx-toastr';
import {Router} from '@angular/router';
import {KeycloakService} from 'keycloak-angular';


@Component({
  selector: 'app-test',
  templateUrl: './type-badge.component.html',
  styleUrls: ['./type-badge.component.css']
})
export class TypeBadgeComponent implements OnInit {

  public typeBadge:any;
  public size:number=10;
  public currentPage:number=0;
  public totalPages:number;
  public pages:Array<number>;
  private currentKeyword: string="";
  public url="./assets/badge/service.png"
private selectFile :File=null;
  constructor(private typeBadgeService:TypeBadgeService,private http:HttpClientModule,private toastr: ToastrService,protected router: Router, protected keycloakAngular: KeycloakService) { }

  ngOnInit() {

    this.onGetTypeBadge();
  }

  onGetTypeBadge(){
    this.typeBadgeService.getTypeBadges(this.currentPage,this.size)
      .subscribe(data=>{

        this.totalPages=data["page"].totalPages;
        this.pages=new Array<number>(this.totalPages);
        this.typeBadge=data;
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

    this.typeBadgeService.getTypeBadgesCherche(this.currentPage,this.size,this.currentKeyword)
      .subscribe(data=>{
        this.totalPages=data["page"].totalPages;
        this.pages=new Array<number>(this.totalPages);
        this.typeBadge=data;
        //console.log(this.totalPages);
      },err => {
        console.log(err)
      })

  }

  onDeleteProduct(p: any) {
    let conf=confirm("Etes vous sûre?");
    if(conf){
      this.showNotification('top', 'center',4);
      this.typeBadgeService.deleteTypeBadges(p._links.self.href)
        .subscribe(data=>{
          this.chercherTitre();
        },err => {
          console.log(err);
        }

      );
    }
  }


  onSaveProduct(value: any) {
   // console.log(value);
    this.typeBadgeService.saveTypeBadges(this.typeBadgeService.host+"/TYPE-BADGE-SERVICE/typeBadges",value)
      .subscribe(resp=>{
        this.showNotification('top', 'center',2);
          // console.log(resp)
        this.onGetTypeBadge();
    },err => {
      console.log(err);
    })

  }

  onselectFile(e) {
    if(e.target.files){
      var reader=new FileReader();
      reader.readAsDataURL(e.target.files[0]);
      this.selectFile=<File>e.target.files[0];

      reader.onload=(event:any)=>{
        this.url=event.target.result;
        localStorage.setItem("recent-image",event.target.result);
        console.log(event.target.result);
      }
    }
  }

  showNotification(from, align,color){

    switch(color){

      case 2:
        this.toastr.success('<span class="now-ui-icons ui-1_bell-53"></span> Type de badge bien ajouter.', '', {
          timeOut: 8000,
          closeButton: true,
          enableHtml: true,
          toastClass: "alert alert-success alert-with-icon",
          positionClass: 'toast-' + from + '-' +  align
        });
        break;
      case 3:
        this.toastr.warning('<span class="now-ui-icons ui-1_bell-53"></span> Type de badge bien modifier.', '', {
          timeOut: 8000,
          closeButton: true,
          enableHtml: true,
          toastClass: "alert alert-warning alert-with-icon",
          positionClass: 'toast-' + from + '-' +  align
        });
        break;
      case 4:
        this.toastr.error('<span class="now-ui-icons ui-1_bell-53"></span> Type de badge bien supprimer.', '', {
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

