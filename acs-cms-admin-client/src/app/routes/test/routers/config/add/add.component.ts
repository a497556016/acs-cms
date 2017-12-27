import { Component } from "@angular/core";
import { OnInit } from "@angular/core";
import { _HttpClient } from "@delon/theme/services/http/http.client";
import { NzMessageService } from "ng-zorro-antd";


@Component({
    templateUrl: 'add.component.html'
})
export class RouterAddComponent implements OnInit {

    router: any = {};
    routerData: Array<any> = [];
    categoryData: Array<any> = [];

    ngOnInit(): void {
        this.loadRouterData();
        this.loadCategoryData();
    }

    loadRouterData() {
        this.http.get('router/selectAllParents', {}).subscribe(res => {
            if (res.code == 1) {
                this.routerData = res.data;
            }
        });
    }

    loadCategoryData() {
        this.http.get('category/selectAll', {}).subscribe(res => {
            if (res.code == 1) {
                this.categoryData = res.data;
            }
        });
    }

    submitRouter() {
        this.http.post('router/saveRouter', {}, this.router).subscribe(res => {
            if (res.code == 1) {
                this.msg.success(res.msg)
            }
        });
    }

    isLeafChange($event) {
        if (!$event) {
            this.router.categoryId = null;
        }
    }

    selectPId(pId) {
        if (pId) {
            const res = this.routerData.filter(rd => rd.id == pId);
            if (res.length > 0) {
                this.router.link = res[0].link + '/';
            }
        }
    }

    constructor(
        private http: _HttpClient,
        private msg: NzMessageService
    ) {

    }
}