import { Component, OnInit } from "@angular/core";
import { _HttpClient } from "@delon/theme/services/http/http.client";
import { Router } from "@angular/router";


@Component({
    templateUrl : 'config.component.html'
})
export class RouterConfigComponent implements OnInit {
    
    args:any = {}

    _allChecked:boolean = false;
    _loading:boolean = false;
    _total:number = 0;
    _pageIndex:number = 1;
    _pageSize:number = 10;
    _rowData:Array<any> = [];

    ngOnInit(): void {
        this.load();
    }

    addRouter(){
        this.router.navigate(['test/router/config/add'])
    }

    load(pageIndex?:number){
        if (typeof pageIndex !== 'undefined') {
            this._pageIndex = pageIndex || 1;
        }
        this._loading = true;

        this.args.current = this._pageIndex;
        this.args.size = this._pageSize

        this.http.get('router/selectByPage',this.args).subscribe(res => {
            this._loading = false;
            if(res.code == 1){
                this._rowData = res.data.records;
            }
        });
    }

    constructor(
        private http: _HttpClient,
        private router: Router
    ){}
}