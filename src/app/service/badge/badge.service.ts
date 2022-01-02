import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders , HttpClientModule} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BadgeService {



  //public host:string="http://localhost:8888" ;
  //public host:string="http://192.168.43.208"
  public host:string="/api"
  constructor(private httpClient:HttpClient) { }


  public getGroupe(){
    return this.httpClient.get(this.host+"/BADGES-SERVICE/groupe");
  }

  public getBadge(page:number,size:number){
    return this.httpClient.get(this.host+"/BADGES-SERVICE/fullBadge?page="+page+"&size="+size);
  }
  public getBadgeId(id:number){
    return this.httpClient.get(this.host+"/BADGES-SERVICE/fullBadge/"+id);
  }

  public getBadgesCherche(page:number,size:number,codeBarre :String,nom:String,prenom:String,cin:String,organisme:String,groupe:String,typeBadge:String){
    return this.httpClient.get(this.host+"/BADGES-SERVICE/byAttres?codeBarre="+codeBarre+"&nom="+nom+"&prenom="+prenom+"&cin="+cin+"&organisme="+organisme+"&groupe="+groupe+"&acces="+typeBadge+"&page="+page+"&size="+size);
  }

  public deleteVente(url){
    return this.httpClient.delete(url);
  }
  public saveVente(url,data){
    console.log(data);
    return this.httpClient.post(this.host+"/BADGES-SERVICE/saveBadgesCb",data);
  }
  public updateBadges(url,data){
    console.log(data);
    return this.httpClient.post(this.host+"/BADGES-SERVICE/updateBadgesCb",data);
  }

  public getVenteDetail(article:number){
    return this.httpClient.get(this.host+"/VENTE-SERVICE/fullVente/"+article);
  }
  public saveVenteImpression(url,data){

    console.log("ici "+data);
    return this.httpClient.post(this.host+"/IMPRESSION-BADGE-SERVICE/badgeImpressions",data);
  }
  public checkExist(url,data){
    console.log(data);
    return this.httpClient.get(this.host+"/BADGES-SERVICE/badgeExist/"+data);
  }
  public badgeCode(url,data){
    console.log(data);
    return this.httpClient.get(this.host+"/BADGES-SERVICE/badgeCode/"+data);
  }
}
