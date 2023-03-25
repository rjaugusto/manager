import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AcoesComponent } from './components/acoes/acoes.component';
import { DashboardContentComponent } from './components/dashboard-content/dashboard-content.component';

const routes: Routes = [
  { path: '', component: DashboardContentComponent },
  { path: 'acoes', component: AcoesComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

