import { Routes } from '@angular/router';
import { PublicLayout } from './layouts/public-layout/public-layout';
import { DashboardLayout } from './layouts/dashboard-layout/dashboard-layout';
import { LandingPage } from './pages/public/landing-page/landing-page';
import { DashboardHome } from './pages/dashboard/dashboard-home/dashboard-home';
import { About } from './pages/public/about/about';
import { Faculties } from './pages/public/faculties/faculties';
import { StudyProgrammes } from './pages/public/study-programmes/study-programmes';
import { Subject } from './pages/public/subject/subject';
import { LoginComponent } from './pages/public/login/login';
import { RegisterComponent } from './pages/public/register/register';
import { authGuard } from './auth.guard';
import { Subjects } from './pages/dashboard/subjects/subjects';
import { Activities } from './pages/dashboard/activities/activities';
import { Syllabus } from './pages/dashboard/syllabus/syllabus';
import { Enrolment } from './pages/dashboard/enrolment/enrolment';
import { Documents } from './pages/dashboard/documents/documents';
import { Schedule } from './pages/dashboard/schedule/schedule';
import { Library } from './pages/dashboard/library/library';
import { Supplies } from './pages/dashboard/supplies/supplies';
import { StaffManagement } from './pages/dashboard/staff-management/staff-management';
import { Settings } from './pages/dashboard/settings/settings';
import { Courses } from './pages/dashboard/courses/courses';
import { Grading } from './pages/dashboard/grading/grading';
import { Students } from './pages/dashboard/students/students';
import { OrganisationManagement } from './pages/dashboard/organisation-management/organisation-management';
import { StudyProgrammesManagement } from './pages/dashboard/study-programmes-management/study-programmes-management';
import { UserManagement } from './pages/dashboard/user-management/user-management';
import { TeacherAssignment } from './pages/dashboard/teacher-assignment/teacher-assignment';
import { SubjectRealisations } from './pages/dashboard/subject-realisations/subject-realisations';
import { ExaminationPeriods } from './pages/dashboard/examination-periods/examination-periods';
import { ExamRegistration } from './pages/dashboard/exam-registration/exam-registration';

export const routes: Routes = [
    {
        path: '',
        component: PublicLayout,
        children: [
            { path: '', component: LandingPage },
            { path: 'about', component: About },

            { path: 'faculties', component: Faculties },
            { path: 'faculties/:facultyId', component: Faculties },
            { path: 'faculties/:facultyId/study-programmes/:studyProgrammeId', component: Faculties },

            { path: 'study-programmes', component: StudyProgrammes },
            { path: 'study-programmes/:id', component: StudyProgrammes },

            { path: 'subject/:id', component: Subject },

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

            { path: 'staff-management', component: StaffManagement, canActivate: [authGuard,], data: { requiredRole: ['ROLE_ADMIN'] } },
            { path: 'organisation-management', component: OrganisationManagement, canActivate: [authGuard,], data: { requiredRole: ['ROLE_ADMIN'] } },
            { path: 'study-programmes-management', component: StudyProgrammesManagement, canActivate: [authGuard,], data: { requiredRole: ['ROLE_ADMIN'] } },
            { path: 'user-management', component: UserManagement, canActivate: [authGuard,], data: { requiredRole: ['ROLE_ADMIN'] } },
            { path: 'teacher-assignment', component: TeacherAssignment, canActivate: [authGuard,], data: { requiredRole: ['ROLE_ADMIN'] } },
            { path: 'subject-realisations', component: SubjectRealisations, canActivate: [authGuard,], data: { requiredRole: ['ROLE_ADMIN'] } },
            { path: 'examination-periods', component: ExaminationPeriods, canActivate: [authGuard,], data: { requiredRole: ['ROLE_ADMIN'] } },

            { path: 'subjects', component: Subjects, canActivate: [authGuard], data: { requiredRole: ['ROLE_STUDENT'] } },
            { path: 'activities', component: Activities, canActivate: [authGuard], data: { requiredRole: ['ROLE_STUDENT'] } },
            { path: 'exam-registration', component: ExamRegistration, canActivate: [authGuard], data: { requiredRole: ['ROLE_STUDENT'] } },

            { path: 'syllabus', component: Syllabus, canActivate: [authGuard], data: { requiredRole: ['ROLE_PROFESSOR'] } },
            { path: 'students', component: Students, canActivate: [authGuard], data: { requiredRole: ['ROLE_PROFESSOR'] } },
            { path: 'courses', component: Courses, canActivate: [authGuard], data: { requiredRole: ['ROLE_PROFESSOR'] } },
            { path: 'grading', component: Grading, canActivate: [authGuard], data: { requiredRole: ['ROLE_PROFESSOR'] } },

            { path: 'enrolment', component: Enrolment, canActivate: [authGuard], data: { requiredRole: ['ROLE_STUDENT_SERVICE'] } },
            { path: 'documents', component: Documents, canActivate: [authGuard], data: { requiredRole: ['ROLE_STUDENT_SERVICE'] } },
            { path: 'schedule', component: Schedule, canActivate: [authGuard], data: { requiredRole: ['ROLE_STUDENT_SERVICE'] } },
            { path: 'library', component: Library, canActivate: [authGuard], data: { requiredRole: ['ROLE_STUDENT_SERVICE'] } },
            { path: 'supplies', component: Supplies, canActivate: [authGuard], data: { requiredRole: ['ROLE_STUDENT_SERVICE'] } },

            { path: 'settings', component: Settings, canActivate: [authGuard] }
        ]
    }
];
