import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders , HttpClientModule} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PointageService {



  //public host:string="http://localhost:8888"
  public host:string="/api"
  // public host:string="http://192.168.43.208"
  constructor(private httpClient:HttpClient) { }

  public getTypeBadges(page:number,size:number){
    return this.httpClient.get(this.host+"/POINTAGE-SERVICE/pointages?page="+page+"&size="+size);
  }
  public getTypeBadgesCherche(page:number,size:number,codeBarre :String,porte:String,tourniquet:String,typeTitreAcces:String,autorization:String,dateStart:String,dateEnd:String){
    return this.httpClient.get(this.host+"/POINTAGE-SERVICE/pointages/search/byAttre?codeBarre="+codeBarre+"&porte="+porte+"&tourniquet="+tourniquet+"&typetitreacces="+typeTitreAcces+"&autorization="+autorization+"&startDate="+dateStart+"&endDate="+dateEnd+"&page="+page+"&size="+size);
  }

}
