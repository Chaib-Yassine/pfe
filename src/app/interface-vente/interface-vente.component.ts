import { Component, OnInit } from '@angular/core';

import validate = WebAssembly.validate;
import {AfficheVenteService} from '../service/affiche-vente/affiche-vente.service';
import {InterfaceVenteService} from '../service/interface-vente/interface-vente.service';
import {DatePipe} from '@angular/common';
import {Globals} from '../utility/globals';
import {ToastrService} from 'ngx-toastr';
import {Router} from '@angular/router';



@Component({
  selector: 'app-test',
  templateUrl: './interface-vente.component.html',
  styleUrls: ['./interface-vente.component.css']
})
export class InterfaceVenteComponent implements OnInit {

  public article:any;
  public size:number=10;
  public currentPage:number=0;
  public totalPages:number;
 // public elements:Array<number>;
  public elements:Array<number >;
  public elementsQ:Array<number >;
  public elementsI:Array<number >;
  private currentKeyword: string="";
  public venteDetail:any;
  public totalSomme:number =0;
  public date: Date = new Date();
  public datae:any;
  constructor(private interfaceVenteService:InterfaceVenteService,private toastr: ToastrService,private router:Router) { }

  ngOnInit() {
   this.onGetVente()
  }

  onGetVente(){
    this.interfaceVenteService.getVente(this.currentPage,this.size)
      .subscribe(data=>{

        this.totalPages=data["page"].totalElements;
        this.elements=new Array<number>(this.totalPages);
        this.elementsQ=new Array<number>(this.totalPages);
        this.elementsI=new Array<number>(this.totalPages);
        this.article=data;
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

    this.interfaceVenteService.getVente(this.currentPage,this.size)
      .subscribe(data=>{

        this.totalPages=data["page"].totalPages;
        this.elements=new Array<number>(this.totalPages);
        this.elementsQ=new Array<number>(this.totalPages);
        this.elementsI=new Array<number>(this.totalPages);
        this.article=data;
        //console.log(this.totalPages);
      },err => {
        console.log(err)
      })

  }

  onDeleteProduct(p: any) {
    let conf=confirm("Etes vous sûre?");
    if(conf){
      var elemet=<HTMLInputElement>document.getElementById("qte"+p.id);
      elemet.setAttribute("value",String(-1));
      this.onAddQte(p.id);
    }
  }

  onSaveProduct() {
    //console.log(value);

    this.datae='{"prix_total":'+this.totalSomme+',' +
      '"user_id":"'+Globals.userGlobal+'",' +
      '"ventedate":"'+this.date.getFullYear()+"-"+("0" + (this.date.getMonth() + 1)).slice(-2)+"-"+("0" + (this.date.getDate() + 1)).slice(-2)+'"}'
    this.datae=JSON.parse(this.datae.toString());
    this.interfaceVenteService.saveVente(this.interfaceVenteService.host+"/TYPE-BADGE-SERVICE/typeBadges",this.datae)
      .subscribe(resp=>{
        this.Save(resp["id"]);
    },err => {
      console.log(err);
    })

  }

  onAfficheDetail(p: any) {
    this.interfaceVenteService.getVenteDetail(p)
      .subscribe(data=>{
        this.venteDetail=data;
        //console.log(this.totalPages);
      },err => {
        console.log(err)
      })
  }

  onAddQte(id: any) {
    var elemet=<HTMLInputElement>document.getElementById("qte"+id);
    var somme=<HTMLInputElement>document.getElementById("somme"+id);
    var price=0
    this.article._embedded.articles.forEach(elemetData=>{
      if(elemetData.id==id){
        price=elemetData.price;
      }
    });

    var qte=parseInt(elemet.getAttribute('value'));
    qte+=1;
    elemet.setAttribute("value",String(qte));

    somme.setAttribute("value",String(qte*price));
    this.elements[id]=qte*price;
    this.elementsQ[id]=qte;
    this.elementsI[id]=id;
    this.calcul();
  }

  onRmvQte(id: any) {
    var price=0
    var elemet=document.getElementById("qte"+id);
    var somme=document.getElementById("somme"+id);
    this.article._embedded.articles.forEach(elemetData=>{
      if(elemetData.id==id){
       // console.log(elemetData.price);
        price=elemetData.price;
      }
    });
    var qte=parseInt(elemet.getAttribute('value'));
    if(qte>0){
      qte-=1;
      somme.setAttribute("value",String(qte*price));
      elemet.setAttribute("value",String(qte));
      this.elements[id]=qte*price;
      this.elementsQ[id]=qte;
      this.elementsI[id]=id;
      this.calcul();
    }
  }


   calcul() {
    let sm:number=0;
    this.elements.forEach(function (value){
      sm+=value;
    })
     this.totalSomme=sm;
  }

  Save(value : any) {
    var idArt:any;

    //this.elements.forEach(elementData =>{
      for(let i:number =0;i<this.elements.length;i++)
        if(this.elements[i]){
        this.datae='{"quantity":"'+this.elementsQ[i]+'",' +
          '"price":"'+this.elements[i]+'",' +'"venteIds":"'+value+'",' +
          '"articleID":"'+this.elementsI[i]+'"}';
        this.datae=JSON.parse(this.datae.toString());
        this.interfaceVenteService.saveDetaiVente("",this.datae)
          .subscribe(resp=>{
            var elemet=<HTMLInputElement>document.getElementById("qte"+this.elementsI[i]);
            elemet.setAttribute("value",String(-1));
            this.onAddQte(this.elementsI[i]);
            this.showNotification('top', 'center',2)
            this.router.navigateByUrl("/ticketMultiImpression/"+value);
          },err => {
            console.log(err);
          })
      }


    };


  showNotification(from, align,color){

    switch(color){

      case 2:
        this.toastr.success('<span class="now-ui-icons ui-1_bell-53"></span> Type de Vente bien ajouter.', '', {
          timeOut: 8000,
          closeButton: true,
          enableHtml: true,
          toastClass: "alert alert-success alert-with-icon",
          positionClass: 'toast-' + from + '-' +  align
        });
        break;
      case 3:
        this.toastr.warning('<span class="now-ui-icons ui-1_bell-53"></span> Type de Vente bien modifier.', '', {
          timeOut: 8000,
          closeButton: true,
          enableHtml: true,
          toastClass: "alert alert-warning alert-with-icon",
          positionClass: 'toast-' + from + '-' +  align
        });
        break;
      case 4:
        this.toastr.error('<span class="now-ui-icons ui-1_bell-53"></span> Type de Vente bien supprimer.', '', {
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

