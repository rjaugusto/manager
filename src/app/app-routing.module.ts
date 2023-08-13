import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


import { DashboardContentComponent } from './components/dashboard-content/dashboard-content.component';
import { TransactionComponent } from './components/transaction/transaction/transaction.component';

const routes: Routes = [
  { path: '', component: DashboardContentComponent },
  { path: 'transaction', component: TransactionComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

