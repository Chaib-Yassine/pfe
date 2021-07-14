import { Component, OnInit } from '@angular/core';
import {Globals} from '../../utility/globals';
import {Router} from '@angular/router';
import {KeycloakService} from 'keycloak-angular';
import {TypeBadgeService} from '../../service/type-badge/type-badge.service';

declare interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
}
export const ROUTES: RouteInfo[] = [

    { path: '/dashboard', title: 'Dashboard',  icon: 'design_app', class: '' },
    /*{ path: '/notifications', title: 'Notifications',  icon:'ui-1_bell-53', class: '' },
    { path: '/user-profile', title: 'User Profile',  icon:'users_single-02', class: '' },*/
    { path: '/type-badge', title: 'Type des badges',  icon:'design_palette', class: '' },
    { path: '/type-ticket', title: 'Type des tickets',  icon:'education_paper', class: '' },
    { path: '/affiche-vente', title: 'Les ventes',  icon:'business_money-coins', class: '' },
    { path: '/interface-vente', title: 'interface de vente',  icon:'shopping_cart-simple', class: '' },
    { path: '/ticket-imprimer', title: 'les Tickets Imprimer',  icon:'education_paper', class: '' },
    { path: '/pointage', title: 'Pointage',  icon:'tech_watch-time', class: '' },
    { path: '/badge', title: 'Badges',  icon:'shopping_credit-card', class: '' },
  { path: '/invitation', title: 'Invitations',  icon:'education_agenda-bookmark', class: '' },
];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  menuItems: any[];
  menuItems2: any[];
  menuItems3: any[];
  constructor(private keycloakService: KeycloakService,private router:Router) { }

  ngOnInit() {

    Globals.userGlobal=this.keycloakService.getUsername();
    //console.log(this.keycloakService.getUserRoles());
    Globals.userRole=this.keycloakService.getUserRoles();
    //console.log(Globals.userRole);

    this.menuItems = ROUTES.filter(menuItem => menuItem);
    if(Globals.userRole.indexOf("ADMIN")!=-1){
      this.menuItems = ROUTES.filter(menuItem => menuItem);
    }else
    if(Globals.userRole.indexOf("VENDEUR")!=-1){
       this.menuItems = this.menuItems.filter(item => item['title'] === this.menuItems[4].title)
      this.router.navigateByUrl("/interface-vente/");
     }else
     if(Globals.userRole.indexOf("USER")!=-1){
       //this.menuItems = this.menuItems.filter(item => item['title'] === this.menuItems[1].title && item['title'] === this.menuItems[7].title);
     //  this.menuItems = this.menuItems.filter(item => item['title'] === this.menuItems[7].title);
       this.router.navigateByUrl("/badge/");
       this.menuItems2 = this.menuItems.filter(item => item['title'] === this.menuItems[1].title);
       this.menuItems3= this.menuItems.filter(item => item['title'] == this.menuItems[7].title) ;
       this.menuItems2.push(this.menuItems3[0])
       this.menuItems= this.menuItems2;
     };


    //console.log(this.menuItems);
   // console.log();
  }
  isMobileMenu() {
      if ( window.innerWidth > 991) {
          return false;
      }
      return true;
  }
}
