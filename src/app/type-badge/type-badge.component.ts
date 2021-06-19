import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';


@Component({
  selector: 'app-test',
  templateUrl: './type-badge.component.html',
  styleUrls: ['./type-badge.component.css']
})
export class TypeBadgeComponent implements OnInit {

  public typeBadge:any;
  constructor( private httpClient:HttpClient) { }

  ngOnInit() {
    this.httpClient.get("http://localhost:8888/TYPE-BADGE-SERVICE/typeBadges")
      .subscribe(data=>{
        this.typeBadge=data;
      },err => {
        console.log(err)
      })

  }

}

