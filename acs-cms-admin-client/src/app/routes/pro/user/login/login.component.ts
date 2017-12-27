import { Component, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd';
import { UserLoginService } from '@core/services/user.login.service';
import { _HttpClient } from '@delon/theme';
import { TokenService } from '@core/net/token/token.service';

@Component({
    selector: 'pro-user-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.less']
})
export class ProUserLoginComponent implements OnDestroy {

    form: FormGroup;
    error = '';
    type = 0;
    loading = false;

    constructor(fb: FormBuilder, private router: Router, public msg: NzMessageService,
        private userLoginService: UserLoginService,
        private http:_HttpClient,
        private tokenService: TokenService
    ) {
        this.form = fb.group({
            userName: [null, [Validators.required, Validators.minLength(5)]],
            password: [null, Validators.required],
            mobile: [null, [Validators.required, Validators.pattern(/^1\d{10}$/)]],
            captcha: [null, [Validators.required]],
            remember: [true]
        });
    }

    // region: fields

    get userName() { return this.form.controls.userName; }
    get password() { return this.form.controls.password; }
    get mobile() { return this.form.controls.mobile; }
    get captcha() { return this.form.controls.captcha; }

    // endregion

    switch(ret: any) {
        this.type = ret.index;
    }

    // region: get captcha

    count = 0;
    interval$: any;

    getCaptcha() {
        this.count = 59;
        this.interval$ = setInterval(() => {
            this.count -= 1;
            if (this.count <= 0)
                clearInterval(this.interval$);
        }, 1000);
    }

    // endregion

    submit() {
        this.error = '';
        if (this.type === 0) {
            this.userName.markAsDirty();
            this.password.markAsDirty();
            if (this.userName.invalid || this.password.invalid) return;
        } else {
            this.mobile.markAsDirty();
            this.captcha.markAsDirty();
            if (this.mobile.invalid || this.captcha.invalid) return;
        }
        // mock http
        this.loading = true;
        this.http.get('user/login', {
            username: this.userName.value,
            password: this.password.value
        }).subscribe(res => {
            this.loading = false;
            if (res.code == 1) {

                const roles = [];
                for (let auth of res.data.authorities) {
                    roles.push(auth.auth);
                }
                this.userLoginService.login({
                    username: res.data.username,
                    avatar: res.data.avatar,
                    email: res.data.email,
                    role: roles
                });


                const tokenData = res.data.tokenData;
                this.tokenService.data = {
                    access_token: tokenData.accessToken,
                    expire_time: tokenData.expireTime,
                    refresh_token: tokenData.refreshToken,
                    refresh_token_valid_time: tokenData.refreshTokenValidTime
                };
                this.router.navigate(['dashboard']);
            } else {
                this.error = res.msg;
            }
        });
    }

    ngOnDestroy(): void {
        if (this.interval$) clearInterval(this.interval$);
    }
}
