import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SharedModule } from '@shared/shared.module';

import { ProResultSuccessComponent } from './result/success/success.component';
import { ProResultFailComponent } from './result/fail/fail.component';
import { ProException403Component } from './exception/403.component';
import { ProException404Component } from './exception/404.component';
import { ProException500Component } from './exception/500.component';

const routes: Routes = [
    {
        path: 'result',
        children: [
            { path: 'success', component: ProResultSuccessComponent },
            { path: 'fail', component: ProResultFailComponent }
        ]
    },
    {
        path: 'exception',
        children: [
            { path: '403', component: ProException403Component },
            { path: '404', component: ProException404Component },
            { path: '500', component: ProException500Component }
        ]
    }
];

@NgModule({
    imports: [
        SharedModule,
        RouterModule.forChild(routes)
    ],
    declarations: [
        ProResultSuccessComponent,
        ProResultFailComponent,
        ProException403Component,
        ProException404Component,
        ProException500Component
    ],
    entryComponents: [],
    exports: [
        RouterModule
    ]
})
export class ProModule { }
