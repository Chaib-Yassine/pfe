import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders , HttpClientModule} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class InvitationService {



  //public host:string="http://localhost:8888"
  public host:string="/api"
  constructor(private httpClient:HttpClient) { }

  public getInvitation(page:number,size:number){
    return this.httpClient.get(this.host+"/INVITATION-SERVICE/invitations?page="+page+"&size="+size);
  }
  public getInvitationCherche(page:number,size:number,mc:String){
    return this.httpClient.get(this.host+"/INVITATION-SERVICE/invitations/search/byCodeBarre?mc="+mc+"&page="+page+"&size="+size);
  }

}
