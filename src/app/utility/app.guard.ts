import { Injectable } from '@angular/core';
import {
    ActivatedRouteSnapshot,
    Router,
    RouterStateSnapshot,
} from '@angular/router';
import { KeycloakAuthGuard, KeycloakService } from 'keycloak-angular';
import { AdminLayoutRoutes } from '../layouts/admin-layout/admin-layout.routing';
import { AdminLayoutComponent } from '../layouts/admin-layout/admin-layout.component';




@Injectable({
    providedIn: 'root',
})
export class AuthGuard extends KeycloakAuthGuard {
    constructor(
        protected readonly router: Router,
        protected readonly keycloak: KeycloakService
    ) {
        super(router, keycloak);
    }

   /* public async isAccessAllowed(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot
    ): Promise<boolean> {
        // Force the user to log in if currently unauthenticated.
        if (!this.authenticated) {
            await this.keycloak.login({
                redirectUri: window.location.origin + state.url,
            });
        }

        // Get the roles required from the route.
        const requiredRoles = route.data.roles;

        // Allow the user to to proceed if no additional roles are required to access the route.
        if (!(requiredRoles instanceof Array) || requiredRoles.length === 0) {
            return true;
        }

        // Allow the user to proceed if all the required roles are present.
        return requiredRoles.every((role) => this.roles.includes(role));
    }*/
  isAccessAllowed(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<boolean> {
    return new Promise(async (resolve, reject) => {
      if (!this.authenticated) {
        this.keycloakAngular.login();
        return;
      }
  /*    console.log(AdminLayoutRoutes);
      console.log("------------------");
      console.log(AdminLayoutComponent['']);
      console.log("------------------");


      console.log(route)
      console.log("------------------");*/

      console.log('role restriction given at app-routing.module for this route', route.data.roles);
      console.log('User roles coming after login from keycloak :', this.roles);
      const requiredRoles = route.data.roles;
      let granted: boolean = false;
      if (!requiredRoles || requiredRoles.length === 0) {
        granted = true;
      } else {
        for (const requiredRole of requiredRoles) {
          if (this.roles.indexOf(requiredRole) > -1) {
            granted = true;
            break;
          }
        }
      }

      if(granted === false) {
        this.router.navigate(['/']);
      }
      resolve(granted);

    });
  }
}
