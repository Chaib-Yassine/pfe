import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders , HttpClientModule} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TypeTicketService {



 // public host:string="http://localhost:8888"
  public host:string="/api"
  // public host:string="http://192.168.43.208"
  constructor(private httpClient:HttpClient) { }

  public getTypeBadges(page:number,size:number){
    return this.httpClient.get(this.host+"/ARTICLE-SERVICE/articles?page="+page+"&size="+size);
  }
  public getTypeBadgesCherche(page:number,size:number,mc:String){
    return this.httpClient.get(this.host+"/ARTICLE-SERVICE/articles/search/byDesignation?mc="+mc+"&page="+page+"&size="+size);
  }
  public deleteTypeBadges(url){
    return this.httpClient.delete(url);
  }
  public saveTypeBadges(url,data){
    return this.httpClient.post(this.host+"/ARTICLE-SERVICE/articles",data);
  }
  public updateTypeBadges(url,data){
    return this.httpClient.post(this.host+"/ARTICLE-SERVICE/articles",data);
  }
}
