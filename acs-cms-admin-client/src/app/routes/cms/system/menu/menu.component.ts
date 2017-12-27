import { Component, OnInit } from "@angular/core";
import { _HttpClient } from "@delon/theme/services/http/http.client";


@Component({
    templateUrl: 'menu.component.html'
})
export class MenuComponent implements OnInit {

    args: any = {};

    pi = 1;
    ps = 10;
    total = 200; // mock total
    list = [];
    loading = false;

    load(pi?: number) {
        if (typeof pi !== 'undefined') {
            this.pi = pi || 1;
        }

        this.loading = true;

        this.http.get('menu/selectAllMenus', this.args).subscribe(res => {
            this.loading = false;
            this.list = res;
            this.total = res ? res.length : 0;
            this.list.forEach(item => {
                this.expandDataCache[item.id] = this.convertTreeToList(item);
            });
        });
    }

    clear() {
        this.args = {};
        this.load(1);
    }

    expandDataCache = {};

    collapse(array, data, $event) {
        if ($event === false) {
            if (data.children) {
                data.children.forEach(d => {
                    const target = array.find(a => a.id === d.id);
                    target.expand = false;
                    this.collapse(array, target, false);
                });
            } else {
                return;
            }
        }
    }

    convertTreeToList(root) {
        const stack = [], array = [], hashMap = {};
        stack.push({ ...root, level: 0, expand: false });

        while (stack.length !== 0) {
            const node = stack.pop();
            this.visitNode(node, hashMap, array);
            if (node.children) {
                for (let i = node.children.length - 1; i >= 0; i--) {
                    stack.push({ ...node.children[i], level: node.level + 1, expand: false, parent: node });
                }
            }
        }

        return array;
    }

    visitNode(node, hashMap, array) {
        if (!hashMap[node.id]) {
            hashMap[node.id] = true;
            array.push(node);
        }
    }

    ngOnInit(): void {
        this.load();
    }
    constructor(
        private http: _HttpClient
    ) { }
}