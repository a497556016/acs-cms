import { Injectable } from '@angular/core';
import { TokenData } from './token.type';
import { _HttpClient } from '@delon/theme';

/** 存储键 */
const KEY = '_user';
/**
 * 基于Token认证，在前后端分离非常普通，本身只提供一个接口的形式展示如果优雅处理HTTP请求
 */
@Injectable()
export class TokenService {
    constructor(
        private http: _HttpClient
    ){}
    /**
     * 保存
     */
    set data(token: TokenData) {
        localStorage.setItem(KEY, JSON.stringify(token));
    }

    /**
     * 获取
     */
    get data(): TokenData {
        return (JSON.parse(localStorage.getItem(KEY) || '{}') || {}) as TokenData;
    }

    refreshToken(refreshToken) {
        this.http.get('user/refreshToken',{
            refreshToken : refreshToken
        }).subscribe(res => {
            if(res.code == 1){
                let tokenData:TokenData = {
                    access_token : res.data.accessToken,
                    expire_time : res.data.expireTime,
                    refresh_token : res.data.refreshToken,
                    refresh_token_valid_time : res.data.refreshTokenValidTime
                };
                this.data = tokenData;
                alert('token刷新成功啦！')
            }
        });
    }

    logout() {
        localStorage.removeItem(KEY);
        console.log('logout');
    }
}
