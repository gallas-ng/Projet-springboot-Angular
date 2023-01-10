import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TutorialsListComponent } from './components/tutorials-list/tutorials-list.component';
import { StudentsListComponent } from './components/student-list/student-list.component';
import { TutorialDetailsComponent } from './components/tutorial-details/tutorial-details.component';
import { StudentDetailsComponent } from './components/student-details/student-details.component';
import { AddTutorialComponent } from './components/add-tutorial/add-tutorial.component';
import { AddStudentComponent } from './components/add-student/add-student.component';
import { GroupListComponent } from './group-list/group-list.component';

const routes: Routes = [
  { path: '', redirectTo: 'tutorials', pathMatch: 'full' },
  { path: 'tutorials', component: TutorialsListComponent },
  { path: 'students', component: StudentsListComponent },
  { path: 'tutorials/:id', component: TutorialDetailsComponent },
  { path: 'students/:id', component: StudentDetailsComponent },
  { path: 'add', component: AddTutorialComponent },
  { path: 'addS', component: AddStudentComponent },
  { path: 'groups', component: GroupListComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }