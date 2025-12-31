import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from './auth.service';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  const requiredRoles = route.data['requiredRole'] as string[] | undefined;

  if (authService.isLoggedIn()) {

    if (!requiredRoles || requiredRoles.length === 0) {
      return true;
    }
    const hasRequiredRole = requiredRoles.some(role => authService.hasRole(role));
    if (hasRequiredRole) {
      return true;
    } else {
      alert('You don\'t have the rights to access this page.');
      return false;
    }
  } else {
    router.navigate(['/login'], { queryParams: { returnUrl: state.url } });
    return false;
  }
};