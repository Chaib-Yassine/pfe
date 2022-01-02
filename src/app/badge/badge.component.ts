import { Component, OnInit } from '@angular/core';

import validate = WebAssembly.validate;

import {DatePipe} from '@angular/common';
import {BadgeService} from '../service/badge/badge.service';
import {TypeBadgeService} from '../service/type-badge/type-badge.service';
import {Router} from '@angular/router';
import {KeycloakService} from 'keycloak-angular';
import {Globals} from '../utility/globals';
import * as XLSX from 'xlsx';
import {AfficheVenteService} from '../service/affiche-vente/affiche-vente.service';
import {ToastrService} from 'ngx-toastr';


@Component({
  selector: 'app-test',
  templateUrl: './badge.component.html',
  styleUrls: ['./badge.component.css']
})
export class BadgeComponent implements OnInit {

  public badge:any;
  public typeBadge:any;
  public typeBadgeAdd:any;
  public groupeAll:any;
  public size:number=10;
  public currentPage:number=0;
  public totalPages:number;
  // public elements:Array<number>;
  public pages:Array<number >;
  private currentKeyword: string="";
  public venteDetail:any;
  public totalSomme:number =0;
  public date: Date = new Date();
  private  nom:String="";
  private  prenom:String="";
  private  cin:String="";
  private  organisme:String="";
  private  groupe:String="";
  private  codebare:String="";
  private  typeBadgeId:String="";
  public userZ = "";
  public datae:any;
  public excel :any ="";
  public nbrElement:any="Selectionner fichier...";
  public etat :number=1;
  public badgeE:any;
  constructor(private badgeService:BadgeService,private typeBadgeService:TypeBadgeService,private router:Router,private keycloakService: KeycloakService,private toastr: ToastrService) { }


  ngOnInit() {
    //this.onGetVente();
    this.chercherTitre();
      this.onGetTypeBadge();
    this.userZ=Globals.userGlobal;
    this.onGroupe();

  }

  onGetVente(){
    //console.log(this.keycloakService.getUsername());
    this.badgeService.getBadge(this.currentPage,this.size)
      .subscribe(data=>{

        this.totalPages=data["totalPages"];
        this.pages=new Array<number>(this.totalPages);
        this.badge=data;
      },err => {
        console.log(err)
      })

  }
  onGetTypeBadge(){
    this.typeBadgeService.getTypeBadges(this.currentPage,this.size)
      .subscribe(data=>{
        this.typeBadge=data;
        //console.log(this.totalPages);
      },err => {
        console.log(err)
      })
  }
  onGroupe(){
    this.badgeService.getGroupe()
      .subscribe(data=>{
        this.groupeAll=data;
       // console.log(data);
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
    this.currentPage=0;
    this.codebare=value.codebarre;
    //console.log("code barre "+this.codebare);
    this.nom=value.nom;
    this.prenom=value.prenom;
    this.cin=value.cin;
    this.groupe=value.groupe;
    this.organisme=value.organisme;
    this.typeBadgeId=value.typeBadge;
    console.log(this.typeBadgeId)
    this.chercherTitre();
  }
  chercherTitre() {
    this.badgeService.getBadgesCherche(this.currentPage,this.size,this.codebare,this.nom,this.prenom,this.cin,this.organisme,this.groupe,this.typeBadgeId)
      .subscribe(data=>{

        this.totalPages=data["totalPages"];
        this.pages=new Array<number>(this.totalPages);
        console.log(data);
        this.badge=data;
        //console.log(this.totalPages);
      },err => {
        console.log(err)
      })

  }

  onDeleteProduct(p: any) {
    let conf=confirm("Etes vous sûre?");
    if(conf){
      //:console.log(p);
      /*  this.interfaceVenteService.deleteVente(p._links.self.href)
          .subscribe(data=>{
            this.chercherTitre();
          },err => {
            console.log(err);
          }

        );*/
      //console.log(p.id);

    }
  }

  onSaveProduct(value: any) {
    this.badgeService.checkExist("",value.cin)
      .subscribe(resp=>{
        console.log(resp);
        if (resp==true){
          this.showNotification("top", "center",5)
          var r = confirm("Ce badge exist déja, voulez vous l'ajouter !!");
          if (r == true) {
            value.id_user=Globals.userGlobal;
            this.badgeService.saveVente("",value)
              .subscribe(resp=>{
                this.onGetVente();
                this.onGroupe();
                this.showNotification("top", "center",2)
                // console.log(resp)
              },err => {
                console.log(err);
              })
          }
        }else {
          value.id_user=Globals.userGlobal;
          this.badgeService.saveVente("",value)
            .subscribe(resp=>{
              this.onGetVente();
              this.onGroupe();
              this.showNotification("top", "center",2)
              // console.log(resp)
            },err => {
              console.log(err);
            })
        }
      },err => {
        console.log(err);
      })
  }


  onImpression(value ) {

    this.datae='{"idBadge":"'+value.id+'",' +
                '"idUser":"'+Globals.userGlobal+'",' +
               '"date":"'+this.date.getFullYear()+"-"+("0" + (this.date.getMonth() + 1)).slice(-2)+"-"+("0" + (this.date.getDate() + 1)).slice(-2)+'"}'
    this.datae=JSON.parse(this.datae);
    if (value.badge_impression.length>0){
      if(confirm("Voulez vous changer le Code barre")){
        this.badgeService.badgeCode("",value.id)
          .subscribe(resp=>{ },err => {
            console.log(err);
          })
      }
    }

   //
    this.badgeService.saveVenteImpression("",this.datae)
      .subscribe(resp=>{
        this.router.navigateByUrl("/badgeImpression/"+value.id);
        this.onGetVente();
        console.log(resp)
      },err => {
        console.log(err);
      })

  }

  multiPrint(value: any) {
    //console.log(value["groupe"]);
    this.badgeService.getBadgesCherche(0,2000,"","","","","",value["groupe"],"")
      .subscribe(data=>{
        //console.log("ddddd"+data["id"]);
        data['content'].forEach(elementData =>{
            this.datae='{"idBadge":'+elementData.id+',' +
              '"idUser":"'+Globals.userGlobal+'",' +
              '"date":"'+this.date.getFullYear()+"-"+("0" + (this.date.getMonth() + 1)).slice(-2)+"-"+("0" + (this.date.getDate() + 1)).slice(-2)+'"}'
            //console.log(this.datae);

          this.datae=JSON.parse(this.datae.toString());
          //console.log(this.datae)
          this.badgeService.saveVenteImpression("",this.datae)
            .subscribe(resp=>{
              console.log(resp)
            },err => {
              console.log(err);
            })
          });
        this.onGetVente();
        this.router.navigateByUrl("/badgeMultiImpression/"+value["groupe"]);
      },err => {
        console.log(err)
      })

   // window.open( "/badgeMultiImpression/"+value["typeBadge"] );
  }


  upladFile() {
    if(this.excel!=""){
      for (let i = 1; i < this.excel.length; i++) {
        this.typeBadgeService.getTypeBadgesCherche1(0,10,this.excel[i][5])
          .subscribe(data=>{


            this.badgeService.checkExist("",this.excel[i][2])
              .subscribe(resp=>{
                this.typeBadgeAdd=data["_embedded"].typeBadges[0].id;
                this.datae='{"nom":"'+this.excel[i][0]+'",' +
                  '"prenom":"'+this.excel[i][1]+'",' +'"cin":"'+this.excel[i][2]+'",' +
                  '"organisme":"'+this.excel[i][3]+'","groupe":"'+this.excel[i][4]+'","typebadgeid":"'+this.typeBadgeAdd+'","id_user":"'+Globals.userGlobal+'","etat":"true"}';
                this.datae=JSON.parse(this.datae.toString());
                if (resp==true){
                  this.showNotification("top", "center",5)
                  var r = confirm("Ce badge exist déja, voulez vous l'ajouter !!");
                  if (r == true) {
                   // this.datae.id_user=Globals.userGlobal;
                    this.badgeService.saveVente("",this.datae)
                      .subscribe(resp=>{
                        this.onGetVente();
                        this.onGroupe();
                        this.showNotification("top", "center",2)
                        // console.log(resp)
                      },err => {
                        console.log(err);
                      })
                  }
                }else {
                 // this.datae.id_user=Globals.userGlobal;
                  this.badgeService.saveVente("",this.datae)
                    .subscribe(resp=>{
                      this.onGetVente();
                      this.onGroupe();
                      this.showNotification("top", "center",2)
                      // console.log(resp)
                    },err => {
                      console.log(err);
                    })
                }
              },err => {
                console.log(err);
              })

          },err => {
            console.log(err)
          })



      }
      this.showNotification("top", "center",1);
    }else {
      alert("Selectionner un fichier excel")
    }

  }

  onFileChange(e) {
    const target :DataTransfer = <DataTransfer>(e.target);
    if (target.files.length !== 1) throw new Error('Cannot use multiple files');
    const reader: FileReader = new FileReader();

    reader.onload = (e: any) => {
      const bstr: string = e.target.result;

    const wb: XLSX.WorkBook = XLSX.read(bstr, { type: 'binary' });

    const wsname : string = wb.SheetNames[0];

    const ws: XLSX.WorkSheet = wb.Sheets[wsname];

    //console.log(ws);

    this.excel = (XLSX.utils.sheet_to_json(ws, { header: 1 }));
      this.nbrElement="Nombre des élements dans le fichier : "+this.excel.length
   // console.log(this.excel);


  };
    reader.readAsBinaryString(target.files[0]);
    this.showNotification("top", "center",3)
  }


  showNotification(from, align,color){

    switch(color){
      case 1:
        this.toastr.success('<span class="now-ui-icons ui-1_bell-53"></span> List des Badges bien ajouter.', '', {
          timeOut: 8000,
          closeButton: true,
          enableHtml: true,
          toastClass: "alert alert-success alert-with-icon",
          positionClass: 'toast-' + from + '-' +  align
        });
        break;

      case 2:
        this.toastr.success('<span class="now-ui-icons ui-1_bell-53"></span> Badge bien ajouter.', '', {
          timeOut: 8000,
          closeButton: true,
          enableHtml: true,
          toastClass: "alert alert-success alert-with-icon",
          positionClass: 'toast-' + from + '-' +  align
        });
        break;
      case 3:
        this.toastr.warning('<span class="now-ui-icons ui-1_bell-53"></span> List des badges  bien charger.', '', {
          timeOut: 8000,
          closeButton: true,
          enableHtml: true,
          toastClass: "alert alert-warning alert-with-icon",
          positionClass: 'toast-' + from + '-' +  align
        });
        break;
      case 4:
        this.toastr.error('<span class="now-ui-icons ui-1_bell-53"></span> Badge bien supprimer.', '', {
          timeOut: 8000,
          enableHtml: true,
          closeButton: true,
          toastClass: "alert alert-danger alert-with-icon",
          positionClass: 'toast-' + from + '-' +  align
        });
        break;
      case 5:
        this.toastr.error('<span class="now-ui-icons ui-1_bell-53"></span> Badge existe déja.', '', {
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
    value.id_user=Globals.userGlobal;
    this.badgeService.updateBadges("",value)
      .subscribe(resp=>{
        this.showNotification('top', 'center',3);
        //this.onGetTypeBadge();
        this.ngOnInit();
      },err => {
        console.log(err);
      })
    //this.onGetTypeBadge();
   // this.ngOnInit();
    this.etat=1;
  }

  onEditProduct(p: any) {
    this.badgeE=p;
    this.etat=2;
  }
}

