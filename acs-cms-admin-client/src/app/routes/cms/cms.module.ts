import { NgModule } from "@angular/core";
import { SharedModule } from "@shared/shared.module";
import { SystemModule } from "app/routes/cms/system/system.module";

@NgModule({
    imports: [
        SharedModule,
        SystemModule
    ],
    declarations: [
        
    ]
})
export class CMSModule { }