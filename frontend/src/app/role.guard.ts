import { ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { inject } from '@angular/core';
import { createAuthGuard, AuthGuardData } from 'keycloak-angular';

export const roleGuard = createAuthGuard(async (route: ActivatedRouteSnapshot, state: RouterStateSnapshot, authData: AuthGuardData): Promise<boolean | UrlTree> => {
  const { authenticated, grantedRoles, keycloak } = authData;
  const router = inject(Router);

  if (!authenticated) {
    await keycloak.login({
      redirectUri: window.location.origin + state.url
    });
    return false;
  }

  const requiredRole = route.data['requiredRole'] as string;
  
  if (!requiredRole) {
    console.warn('RoleGuard: No requiredRole specified in route data');
    return router.createUrlTree(['/app']);
  }

  const hasRole = 
    grantedRoles.realmRoles.includes(requiredRole) ||
    Object.values(grantedRoles.resourceRoles).some(roles => roles.includes(requiredRole));

  if (!hasRole) {
    return router.createUrlTree(['/app']);
  }

  return true;
});
