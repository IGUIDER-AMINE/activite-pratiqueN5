import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ProductsComponent} from "./ui/products/products.component";
import {CustomersComponent} from "./ui/customers/customers.component";
import {authGuard} from "./guards/auth.guard";
import {OrdersComponent} from "./ui/orders/orders.component";
import {OrderDetailsComponent} from "./ui/orders-details/orders-details.component";

const routes: Routes = [
  {path:"products",component:ProductsComponent,canActivate : [authGuard], data : {roles :['ADMIN']}},
  {path:"customers",component:CustomersComponent,canActivate : [authGuard], data : {roles :['USER']}},
  {path : "orders", component : OrdersComponent, canActivate : [authGuard], data : {roles : ['USER']}},
  {path : "order-details/:id", component : OrderDetailsComponent, canActivate : [authGuard], data : {roles : ['USER']}},
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
