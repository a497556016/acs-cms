import { NgModule } from "@angular/core";
import { SharedModule } from "@shared/shared.module";
import { RoutersModule } from "app/routes/test/routers/router.module";

@NgModule({
    imports: [
        SharedModule,
        RoutersModule
    ],
    declarations: [
        
    ]
})
export class TestModule { }