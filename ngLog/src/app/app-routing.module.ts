import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LogListComponent } from './components/log-list/log-list.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { MaintenanceComponent } from './components/maintenance/maintenance.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomeComponent },
  { path: 'log', component: LogListComponent},
  { path: 'log/:id', component: LogListComponent},
  { path: 'maintenancelog', component: MaintenanceComponent},

  { path: '**', component: NotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
