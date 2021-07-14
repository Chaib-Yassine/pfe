import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders , HttpClientModule} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AfficheVenteService {



 // public host:string="http://localhost:8888" ;
  public host:string="/api"
  //public host:string="http://192.168.43.208"
  constructor(private httpClient:HttpClient) { }

  public getVente(page:number,size:number){
    return this.httpClient.get(this.host+"/VENTE-SERVICE/ventes?page="+page+"&size="+size);
  }
  public getVenteCherche(page:number,size:number,startDate:String,endDate:String){
    return this.httpClient.get(this.host+"/VENTE-SERVICE/ventes/search/byDates?startDate="+startDate+"&endDate="+endDate+"&page="+page+"&size="+size);
  }
  public deleteVente(url){
    return this.httpClient.delete(url);
  }
  public saveVente(url,data){
    let test:string;
    test='{"titre":"testhhhhr","statut": "true"}';
    console.log(test);
    return this.httpClient.post(url,data,{headers:new HttpHeaders().set('Content-Type','application/json')});
  }

  public getVenteDetail(article:number){
    return this.httpClient.get(this.host+"/VENTE-SERVICE/fullVente/"+article);
  }
}
