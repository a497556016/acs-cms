import { Injectable, Injector } from '@angular/core';
import { Router } from '@angular/router';
import { HttpInterceptor, HttpRequest, HttpHandler,
         HttpSentEvent, HttpHeaderResponse, HttpProgressEvent, HttpResponse, HttpUserEvent,
         HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
import { catchError } from 'rxjs/operators';
import { map, mergeMap } from 'rxjs/operators';

import { environment } from '../../../environments/environment';
import { TokenService } from '@core/net/token/token.service';
import { NzMessageService } from 'ng-zorro-antd';

/**
 * 默认HTTP拦截器，其注册细节见 `app.module.ts`
 */
@Injectable()
export class DefaultInterceptor implements HttpInterceptor {
    constructor(private injector: Injector) {}

    private goLogin() {
        const router = this.injector.get(Router);
        this.injector.get(Router).navigate([ 'pro/user/login' ]);
        // window.location.href = '#/pro/user/login';
    }

    intercept(req: HttpRequest<any>, next: HttpHandler):
        Observable<HttpSentEvent | HttpHeaderResponse | HttpProgressEvent | HttpResponse<any> | HttpUserEvent<any>> {
        let header: HttpHeaders = null;
        // 过滤授权与多assets请求
        if (!req.url.includes('user/login') && !req.url.includes('config/getSysConfig') && !req.url.includes('assets/') && !req.params.has('_allow_anonymous')) {
            const curTime = new Date().getTime();
            const tokenService = this.injector.get(TokenService);
            let authData = tokenService.data;
            // 业务处理：无法获取授权，或授权已经过期时放弃当前请求
            if (!authData.access_token) {
                this.goLogin();
                return ErrorObservable.create({ status: 401 });
            }
            //如果access_token已经过期且refresh_token未过期，请求新的token，并暂时用刷新token代替当前token
            else if(authData.expire_time<=curTime&&authData.refresh_token_valid_time>curTime
                &&!req.url.includes('user/refreshToken')){
                    alert('我开始刷新token辣！');
                authData.access_token = authData.refresh_token;
                authData.expire_time = authData.refresh_token_valid_time;
                tokenService.data = authData;
                tokenService.refreshToken(authData.refresh_token);
            }
            // 正常token值放在请求header当中，具体格式以后端为准
            header = req.headers.set('Authorization', `auth ${authData.access_token}`);
        }

        // 统一加上服务端前缀
        let url = req.url;
        if (!url.startsWith('https://') && !url.startsWith('http://')  && !url.includes('assets/')) {
            url = environment.SERVER_URL + url;
        }

        const newReq = req.clone({
            headers: header,
            url: url
        });

        return next.handle(newReq).pipe(
                    mergeMap((event: any) => {
                        // 允许统一对请求错误处理，这是因为一个请求若是业务上错误的情况下其HTTP请求的状态是200的情况下需要
                        if (event instanceof HttpResponse && event.status !== 200) {
                            // 业务处理：observer.error 会跳转至后面的 `catch`
                            // return ErrorObservable.create(event);
                        }
                        if(event instanceof HttpResponse){
                            const res = event.body;
                            if(res.code == 601){
                                const msg = this.injector.get(NzMessageService);
                                msg.info(res.msg);
                                this.goLogin();
                            }
                        }
                        // 若一切都正常，则后续操作
                        return Observable.create(observer => observer.next(event));
                    }),
                    catchError((res: HttpResponse<any>) => {
                        // 业务处理：一些通用操作
                        switch (res.status) {
                            case 401: // 未登录状态码
                                this.goLogin();
                                break;
                            case 200:
                                // 业务层级错误处理
                                console.log('业务错误');
                                break;
                            case 404:
                                // 404
                                break;
                        }
                        // 以错误的形式结束本次请求
                        return ErrorObservable.create(event);
                    })
                );
    }
}
