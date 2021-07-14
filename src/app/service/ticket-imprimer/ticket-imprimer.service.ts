import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders , HttpClientModule} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TicketImprimerService {



 // public host:string="http://localhost:8888"
  public host:string="/api"
 // public host:string="http://192.168.43.208"
  constructor(private httpClient:HttpClient) { }

  public getTicketImprimer(page:number,size:number){
    return this.httpClient.get(this.host+"/TICKET-SERVICE/fullTicket?page="+page+"&size="+size);
  }
  public getTicketImprimerCherche(page:number,size:number,mc:string){
    console.log(mc)
    return this.httpClient.get(this.host+"/TICKET-SERVICE/byNvente?mc="+mc+"&page="+page+"&size="+size);
  }

}
