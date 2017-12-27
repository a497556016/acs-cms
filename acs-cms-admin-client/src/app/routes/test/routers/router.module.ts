import { NgModule } from "@angular/core";
import { SharedModule } from "@shared/shared.module";
import { RouterModule, Routes } from "@angular/router";
import { RouterConfigComponent } from "app/routes/test/routers/config/config.component";
import { RouterAddComponent } from "app/routes/test/routers/config/add/add.component";

const routes: Routes = [
    {
        path: 'router',
        children: [
            { 
                path: 'config', 
                component: RouterConfigComponent
            },
            {
                path: 'config/add',
                component: RouterAddComponent
            }
        ]
    }
]
@NgModule({
    imports: [
        SharedModule,
        RouterModule.forChild(routes)
    ],
    declarations: [
        RouterConfigComponent,
        RouterAddComponent
    ],
    exports: [
        RouterModule
    ]
})
export class RoutersModule { }