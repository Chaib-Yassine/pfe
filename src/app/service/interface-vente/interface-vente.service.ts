import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders , HttpClientModule} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class InterfaceVenteService {



  //public host:string="http://localhost:8888" ;
  //public host:string="http://192.168.43.208"
  public host:string="/api"
  constructor(private httpClient:HttpClient) { }

  public getVente(page:number,size:number){
    return this.httpClient.get(this.host+"/ARTICLE-SERVICE/articles?page="+page+"&size="+size);
  }

  public deleteVente(url){
    return this.httpClient.delete(url);
  }
  public saveVente(url,data){
    return this.httpClient.post(this.host+"/VENTE-SERVICE/ventes",data);
  }

  public getVenteDetail(article:number){
    return this.httpClient.get(this.host+"/VENTE-SERVICE/fullVente/"+article);
  }

  saveDetaiVente(s: string, datae: any) {
    console.log(datae);
    return this.httpClient.post(this.host+"/VENTE-SERVICE/venteDetails",datae);
  }
}
