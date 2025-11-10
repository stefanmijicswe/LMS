import { Routes } from '@angular/router';
import { PublicLayout } from './layouts/public-layout/public-layout';
import { DashboardLayout } from './layouts/dashboard-layout/dashboard-layout';
import { LandingPage } from './pages/public/landing-page/landing-page';
import { DashboardHome } from './pages/dashboard/dashboard-home/dashboard-home';

export const routes: Routes = [

    {
        path: '',
        component: PublicLayout,
        children: [
            { path: '', component: LandingPage },
        ]
    },
    {
        path: 'app',
        component: DashboardLayout,
        children: [
            { path: '', component: DashboardHome },
        ]
    }
];
