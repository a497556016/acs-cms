import { NgModule } from "@angular/core";
import { SharedModule } from "@shared/shared.module";
import { RouterModule, Routes } from "@angular/router";
import { MenuComponent } from "app/routes/cms/system/menu/menu.component";

const routes: Routes = [
    {
        path: 'system',
        children: [
            { path: 'menu', component: MenuComponent }
        ]
    }
]
@NgModule({
    imports: [
        SharedModule,
        RouterModule.forChild(routes)
    ],
    declarations: [
        MenuComponent
    ],
    exports: [
        RouterModule
    ]
})
export class SystemModule { }