import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LibraryComponent} from "./components/library/library.component";
import {NotFoundComponent} from "./components/not-found/not-found.component";

const routes: Routes = [
  {path: '', component: LibraryComponent},
  {path: 'research', component: LibraryComponent},
  {path: '**', component: NotFoundComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
