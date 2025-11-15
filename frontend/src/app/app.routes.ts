import { Routes } from '@angular/router';
import { PublicLayout } from './layouts/public-layout/public-layout';
import { DashboardLayout } from './layouts/dashboard-layout/dashboard-layout';
import { LandingPage } from './pages/public/landing-page/landing-page';
import { DashboardHome } from './pages/dashboard/dashboard-home/dashboard-home';
import { About } from './pages/public/about/about';
import { Faculties } from './pages/public/faculties/faculties';
import { authGuard } from './auth.guard';

export const routes: Routes = [

    {
        path: '',
        component: PublicLayout,
        children: [
            { path: '', component: LandingPage },
            { path: 'about', component: About },
            { path: 'faculties', component: Faculties },
        ]
    },
    {
        path: 'app',
        component: DashboardLayout,
        canActivate: [authGuard],
        children: [
            { path: '', component: DashboardHome },
        ]
    }
];
