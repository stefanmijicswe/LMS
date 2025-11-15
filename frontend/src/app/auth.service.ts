import { Injectable, inject, Signal, signal, effect } from '@angular/core';
import Keycloak from 'keycloak-js';
import { KEYCLOAK_EVENT_SIGNAL, KeycloakEvent, KeycloakEventType, typeEventArgs, ReadyArgs } from 'keycloak-angular';

export interface UserProfile {
  id: string;
  username: string;
  email: string;
  firstName: string;
  lastName: string;
}

const STORAGE_KEY = 'keycloak_user_data';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private keycloak = inject(Keycloak);
  private keycloakEventSignal: Signal<KeycloakEvent> = inject(KEYCLOAK_EVENT_SIGNAL);

  userProfile = signal<UserProfile | null>(null);
  userRoles = signal<string[]>([]);
  isAuthenticated = signal<boolean>(false);

  constructor() {
    this.loadFromStorage();

    effect(() => {
      const event = this.keycloakEventSignal();
      
      if (event.type === KeycloakEventType.Ready) {
        const readyArgs = typeEventArgs<ReadyArgs>(event.args);
        if (readyArgs) this.loadUserData();
      } else if (event.type === KeycloakEventType.AuthRefreshSuccess || 
                 event.type === KeycloakEventType.AuthSuccess) {
        this.loadUserData();
      } else if (event.type === KeycloakEventType.AuthLogout) {
        this.clearUserData();
      }
    });
  }

  loadUserData(): void {
    if (!this.keycloak.authenticated || !this.keycloak.token) {
      this.clearUserData();
      return;
    }

    const token = this.keycloak.idTokenParsed || this.keycloak.tokenParsed;
    const profile: UserProfile | null = token ? {
      id: token.sub || '',
      username: (token as any)['preferred_username'] || (token as any)['username'] || token.sub || '',
      email: (token as any)['email'] || '',
      firstName: (token as any)['given_name'] || '',
      lastName: (token as any)['family_name'] || ''
    } : null;

    const roles: string[] = [];
    if (this.keycloak.tokenParsed) {
      const accessToken = this.keycloak.tokenParsed as any;
      if (accessToken.realm_access?.roles) {
        roles.push(...accessToken.realm_access.roles);
      }
      if (accessToken.resource_access) {
        const clientRoles = Object.values(accessToken.resource_access)
          .flatMap((access: any) => access.roles || []);
        roles.push(...clientRoles);
      }
    }

    this.userProfile.set(profile);
    this.userRoles.set(roles);
    this.isAuthenticated.set(true);
    this.saveToStorage(profile, roles);
  }

  hasRole(role: string): boolean {
    return this.userRoles().includes(role);
  }

  clearUserData(): void {
    this.userProfile.set(null);
    this.userRoles.set([]);
    this.isAuthenticated.set(false);
    localStorage.removeItem(STORAGE_KEY);
  }

  private saveToStorage(profile: UserProfile | null, roles: string[]): void {
    try {
      localStorage.setItem(STORAGE_KEY, JSON.stringify({ profile, roles, timestamp: Date.now() }));
    } catch (error) {
      console.warn('Failed to save to localStorage:', error);
    }
  }

  private loadFromStorage(): void {
    try {
      const stored = localStorage.getItem(STORAGE_KEY);
      if (stored) {
        const data = JSON.parse(stored);
        const oneHour = 60 * 60 * 1000;
        if (Date.now() - data.timestamp < oneHour) {
          this.userProfile.set(data.profile);
          this.userRoles.set(data.roles || []);
          this.isAuthenticated.set(!!data.profile);
        } else {
          localStorage.removeItem(STORAGE_KEY);
        }
      }
    } catch (error) {
      console.warn('Failed to load from localStorage:', error);
    }
  }
}

