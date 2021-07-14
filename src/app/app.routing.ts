import { NgModule } from '@angular/core';
import { CommonModule, } from '@angular/common';
import { BrowserModule  } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';

import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import {BadgeImpressionComponent} from './badgeImpression/badgeImpression.component';
import {BadgeMultiImpressionComponent} from './badgeMultiImpression/badgeMultiImpression.component';
import {AuthGuard} from './utility/app.guard';
import {TicketMultiImpressionComponent} from './ticketMultiImpression/ticketMultiImpression.component';

const routes: Routes =[
  {
    path: '',
    redirectTo: 'dashboard',
    pathMatch: 'full',
  },{
    path: 'ticketMultiImpression/:id',
    component: TicketMultiImpressionComponent
  },
  {
    path: 'badgeImpression/:id',
    component: BadgeImpressionComponent
  },
  {
    path: 'badgeMultiImpression/:id',
    component: BadgeMultiImpressionComponent
  },{
    path: '',
    component: AdminLayoutComponent,
   canActivate: [AuthGuard],
    children: [
        {
      path: '',
      loadChildren: './layouts/admin-layout/admin-layout.module#AdminLayoutModule'
  }
  ]},
  {
    path: '**',
    redirectTo: 'dashboard'
  }
];

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
  ],
})
export class AppRoutingModule { }
