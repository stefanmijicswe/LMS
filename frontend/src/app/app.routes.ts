import { Routes } from '@angular/router';
import { PublicLayout } from './layouts/public-layout/public-layout';
import { DashboardLayout } from './layouts/dashboard-layout/dashboard-layout';
import { LandingPage } from './pages/public/landing-page/landing-page';
import { DashboardHome } from './pages/dashboard/dashboard-home/dashboard-home';
import { About } from './pages/public/about/about';
import { Faculties } from './pages/public/faculties/faculties';
import { authGuard } from './auth.guard';
import { Subjects } from './pages/dashboard/subjects/subjects';
import { Activities } from './pages/dashboard/activities/activities';
import { Notifications } from './pages/dashboard/notifications/notifications';
import { Syllabus } from './pages/dashboard/syllabus/syllabus';
import { Enrolment } from './pages/dashboard/enrolment/enrolment';
import { Documents } from './pages/dashboard/documents/documents';
import { Schedule } from './pages/dashboard/schedule/schedule';
import { Library } from './pages/dashboard/library/library';
import { Supplies } from './pages/dashboard/supplies/supplies';
import { Announcements } from './pages/dashboard/announcements/announcements';
import { StaffManagement } from './pages/dashboard/staff-management/staff-management';
import { Settings } from './pages/dashboard/settings/settings';
import { Courses } from './pages/dashboard/courses/courses';
import { Grading } from './pages/dashboard/grading/grading';
import { Announcement } from './pages/dashboard/announcement/announcement';
import { Students } from './pages/dashboard/students/students';
import { OrganisationManagement } from './pages/dashboard/organisation-management/organisation-management';
import { StudyProgrammesManagement } from './pages/dashboard/study-programmes-management/study-programmes-management';
import { UserManagement } from './pages/dashboard/user-management/user-management';
import { StudyProgrammes } from './pages/public/study-programmes/study-programmes';
import { LoginComponent } from './pages/public/login/login';
import { RegisterComponent } from './pages/public/register/register';

export const routes: Routes = [

    {
        path: '',
        component: PublicLayout,
        children: [
            { path: '', component: LandingPage },
            { path: 'about', component: About },
            { path: 'faculties', component: Faculties },
            { path: 'study-programmes', component: StudyProgrammes },
            { path: 'login', component: LoginComponent },
            { path: 'register', component: RegisterComponent },
        ]
    },
    {
        path: 'app',
        component: DashboardLayout,
        canActivate: [authGuard],
        children: [
            { path: '', component: DashboardHome },

            { path: 'notifications', component: Notifications, canActivate: [authGuard, ], data: { requiredRole: ['ROLE_STUDENT'] }},
            { path: 'subjects', component: Subjects, canActivate: [authGuard, ], data: { requiredRole: ['ROLE_STUDENT'] }},
            { path: 'activities',component: Activities,canActivate: [authGuard, ],data: { requiredRole: ['ROLE_STUDENT'] }},

            { path: 'announcement', component: Announcement, canActivate: [authGuard, ], data: { requiredRole: ['ROLE_TEACHER'] }},
            { path: 'syllabus', component: Syllabus, canActivate: [authGuard, ], data: { requiredRole: ['ROLE_TEACHER'] }},
            { path: 'students', component: Students, canActivate: [authGuard, ], data: { requiredRole: ['ROLE_TEACHER'] }},
            { path: 'courses', component: Courses, canActivate: [authGuard, ], data: { requiredRole: ['ROLE_TEACHER'] }},
            { path: 'grading', component: Grading, canActivate: [authGuard, ], data: { requiredRole: ['ROLE_TEACHER'] }},

            { path: 'enrolment', component: Enrolment, canActivate: [authGuard, ], data: { requiredRole: ['ROLE_STUDENT_SERVICE'] }},
            { path: 'documents', component: Documents, canActivate: [authGuard, ], data: { requiredRole: ['ROLE_STUDENT_SERVICE'] }},
            { path: 'schedule', component: Schedule, canActivate: [authGuard, ], data: { requiredRole: ['ROLE_STUDENT_SERVICE'] }},
            { path: 'library', component: Library, canActivate: [authGuard, ], data: { requiredRole: ['ROLE_STUDENT_SERVICE'] }},
            { path: 'supplies', component: Supplies, canActivate: [authGuard, ], data: { requiredRole: ['ROLE_STUDENT_SERVICE'] }},
            { path: 'announcements', component: Announcements, canActivate: [authGuard, ], data: { requiredRole: ['ROLE_STUDENT_SERVICE'] }},

            { path: 'staff-management', component: StaffManagement, canActivate: [authGuard, ], data: { requiredRole: ['ROLE_ADMIN'] }},
            { path: 'organisation-management', component: OrganisationManagement, canActivate: [authGuard, ], data: { requiredRole: ['ROLE_ADMIN'] }},
            { path: 'study-programmes-management', component: StudyProgrammesManagement, canActivate: [authGuard, ], data: { requiredRole: ['ROLE_ADMIN'] }},
            { path: 'user-management', component: UserManagement, canActivate: [authGuard, ], data: { requiredRole: ['ROLE_ADMIN'] }},

            { path: 'settings', component: Settings, canActivate: [authGuard]}
        ]
    }
];
