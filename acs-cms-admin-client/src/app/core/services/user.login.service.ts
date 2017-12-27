import { Injectable, Injector } from "@angular/core";
import { ACLService } from "@delon/acl";
import { MenuService, User, SettingsService } from "@delon/theme";
import { Router } from "@angular/router";

const _KEY = "_user_details";
interface UserDetail{
    username:string,
    avatar:string,
    email:string,
    role:string[],
}
@Injectable()
export class UserLoginService{
    _user: UserDetail;

    login(val:UserDetail){
        this._user = val;
        this.setSettingUser(val);
        localStorage.setItem(_KEY,JSON.stringify(val));
    }

    get user(){
        if(this._user==null){
            const userJson = localStorage.getItem(_KEY);
            if(userJson!=null){
                this._user = JSON.parse(userJson);
            }
        }
        return this._user;
    }

    private setSettingUser(val:UserDetail){
        this.setting.setUser({
            name:val.username,
            avatar:val.avatar,
            email:val.email
        });
        this.aclService.setRole(val.role);
        this.menuService.resume((item) => {
            item.hide = item.acl && !this.aclService.can({role : item.acl.split(',')});
        });
    }

    logout(){
        this._user = null;
        localStorage.removeItem(_KEY);
        // window.location.href = '#/pro/user/login';
        const router = this.injector.get(Router);
        if(null!=router){
            router.navigate(['pro/user/login']);
        }
    }

    constructor(
        private setting: SettingsService,
        private menuService: MenuService,
        private aclService: ACLService,
        private injector: Injector
    ){}
}