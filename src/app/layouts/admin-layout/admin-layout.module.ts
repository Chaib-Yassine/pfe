import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminLayoutRoutes } from './admin-layout.routing';
import { DashboardComponent } from '../../dashboard/dashboard.component';
import { UserProfileComponent } from '../../user-profile/user-profile.component';
import { NotificationsComponent } from '../../notifications/notifications.component';
import { ChartsModule } from 'ng2-charts';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ToastrModule } from 'ngx-toastr';
import {TypeBadgeComponent} from '../../type-badge/type-badge.component';
import {AfficheVenteComponent} from '../../affiche-vente/affiche-vente.component';
import {BadgeComponent} from '../../badge/badge.component';
import {InterfaceVenteComponent} from '../../interface-vente/interface-vente.component';
import {PointageComponent} from '../../pointage/pointage.component';
import {TicketImprimerComponent} from '../../ticket-imprimer/ticket-imprimer.component';
import {TypeTicketComponent} from '../../type-ticket/type-ticket.component';
import {InvitationComponent} from '../../invitation/invitation.component';
import {BadgeImpressionComponent} from '../../badgeImpression/badgeImpression.component';
import {QRCodeModule} from 'angularx-qrcode';
import {NgxBarcodeModule} from 'ngx-barcode';
import {BadgeMultiImpressionComponent} from '../../badgeMultiImpression/badgeMultiImpression.component';
import {KeycloakAngularModule, KeycloakService} from 'keycloak-angular';
import {HttpClientModule} from '@angular/common/http';
import {TicketMultiImpressionComponent} from '../../ticketMultiImpression/ticketMultiImpression.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    FormsModule,
    ChartsModule,
    NgbModule,
    ToastrModule.forRoot(),
    QRCodeModule,
    NgxBarcodeModule,
    KeycloakAngularModule,
    HttpClientModule

  ],
  declarations: [
    DashboardComponent,
    UserProfileComponent,
    NotificationsComponent,
    TypeBadgeComponent,
    AfficheVenteComponent,
    InterfaceVenteComponent,
    PointageComponent,
    BadgeComponent,
    TicketImprimerComponent,
    TypeTicketComponent,
    InvitationComponent,
    BadgeImpressionComponent,
    BadgeMultiImpressionComponent,
    TicketMultiImpressionComponent

  ]
})

export class AdminLayoutModule {}
