import { Router } from '@angular/router';
import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { SettingsService, MenuService } from '@delon/theme';
import { _HttpClient } from '@delon/theme/services/http/http.client';
import { TokenService } from '@core/net/token/token.service';
import { NzMessageService } from 'ng-zorro-antd';
import { ACLService } from '@delon/acl/services/acl.service';
import { UserLoginService } from '@core/services/user.login.service';

@Component({
  selector: 'app-pages-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {
  valForm: FormGroup;

  constructor(public settings: SettingsService, fb: FormBuilder, private router: Router,
    private http:_HttpClient,
    private tokenService: TokenService,
    private msg: NzMessageService,
    private menuService: MenuService,
    private aclService: ACLService,
    private userLoginService: UserLoginService
  ) {
    this.valForm = fb.group({
      username: [null, Validators.compose([Validators.required])],
      password: [null, Validators.required],
      remember_me: [null]
    });
  }

  submit() {
    // tslint:disable-next-line:forin
    for (const i in this.valForm.controls) {
      this.valForm.controls[i].markAsDirty();
    }
    if (this.valForm.valid) {
      console.log('Valid!');
      console.log(this.valForm.value);
      this.http.get('user/login',{
        username : this.valForm.value.username,
        password : this.valForm.value.password
      }).subscribe(res => {
        if(res.code == 1){
          
          const roles = [];
          for(let auth of res.data.authorities){
            roles.push(auth.auth);
          }
          this.userLoginService.login({
            username : res.data.username,
            avatar: res.data.avatar,
            email: res.data.email,
            role: roles
          });
          
          const tokenData = res.data.tokenData;
          this.tokenService.data = tokenData;
          this.router.navigate(['dashboard']);
        }else{
          this.msg.error(res.msg);
        }
      });
      
    }
  }
}
