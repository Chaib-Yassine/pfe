import { Routes } from '@angular/router';

import { DashboardComponent } from '../../dashboard/dashboard.component';
import { UserProfileComponent } from '../../user-profile/user-profile.component';
import { NotificationsComponent } from '../../notifications/notifications.component';
import {TypeBadgeComponent} from '../../type-badge/type-badge.component';
import {AfficheVenteComponent} from '../../affiche-vente/affiche-vente.component';
import {BadgeComponent} from '../../badge/badge.component';
import {InterfaceVenteComponent} from '../../interface-vente/interface-vente.component';
import {PointageComponent} from '../../pointage/pointage.component';
import {TicketImprimerComponent} from '../../ticket-imprimer/ticket-imprimer.component';
import {TypeTicketComponent} from '../../type-ticket/type-ticket.component';
import {InvitationComponent} from '../../invitation/invitation.component';

export const AdminLayoutRoutes: Routes = [
    { path: 'dashboard',      component: DashboardComponent , data: { roles: ['ADMIN'] }},
    { path: 'user-profile',   component: UserProfileComponent },
    { path: 'notifications',  component: NotificationsComponent },
    { path: 'type-badge',        component: TypeBadgeComponent , data: { roles: ['ADMIN'] }},
    { path: 'affiche-vente',        component: AfficheVenteComponent , data: { roles: ['ADMIN'] }},
    { path: 'interface-vente',        component: InterfaceVenteComponent, data: { roles: ['ADMIN','VENDEUR'] } },
    { path: 'pointage',        component: PointageComponent, data: { roles: ['ADMIN'] } },
    { path: 'badge',        component: BadgeComponent , data: { roles: ['ADMIN','USER'] }},
    { path: 'ticket-imprimer',        component: TicketImprimerComponent, data: { roles: ['ADMIN'] } },
    { path: 'type-ticket',        component: TypeTicketComponent , data: { roles: ['ADMIN'] }},
   { path: 'invitation',        component: InvitationComponent , data: { roles: ['ADMIN'] }},
];
