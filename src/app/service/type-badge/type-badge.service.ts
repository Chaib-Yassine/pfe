import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders , HttpClientModule} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TypeBadgeService {



  //public host:string="http://localhost:8888"
  public host:string="/api"
  // public host:string="http://192.168.43.208"
  constructor(private httpClient:HttpClient) { }

  public getTypeBadges(page:number,size:number){
    return this.httpClient.get(this.host+"/TYPE-BADGE-SERVICE/typeBadges?page="+page+"&size="+size);
  }
  public getTypeBadgesCherche(page:number,size:number,mc:String){

    return this.httpClient.get(this.host+"/TYPE-BADGE-SERVICE/typeBadges/search/byTitre?mc="+mc+"&page="+page+"&size="+size);
  }
  public getTypeBadgesCherche1(page:number,size:number,mc:String){
   // console.log(mc)
    return this.httpClient.get(this.host+"/TYPE-BADGE-SERVICE/typeBadges/search/byTitre1?mc="+mc+"&page="+page+"&size="+size);
  }
  public deleteTypeBadges(url){
    return this.httpClient.delete(url);
  }
  public saveTypeBadges(url,data){

   //console.log("back=> "+data);
    return this.httpClient.post(this.host+"/TYPE-BADGE-SERVICE/typeBadges",data);
  }

  updatTypeBadges(url,data) {
  //  console.log("back=> "+data)
    return this.httpClient.post(this.host+"/TYPE-BADGE-SERVICE/typeBadges",data);
  }
}
