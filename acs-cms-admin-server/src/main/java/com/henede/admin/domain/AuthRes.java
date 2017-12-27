package com.henede.admin.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author HeShaowei
 * @since 2017-12-26
 */
@TableName("sys_auth_res")
public class AuthRes extends Model<AuthRes> {

    private static final long serialVersionUID = 1L;

    @TableId("auth_id")
	private Integer authId;
	@TableField("res_id")
	private Integer resId;


	public Integer getAuthId() {
		return authId;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
	}

	public Integer getResId() {
		return resId;
	}

	public void setResId(Integer resId) {
		this.resId = resId;
	}

	public static final String AUTH_ID = "auth_id";

	public static final String RES_ID = "res_id";

	@Override
	protected Serializable pkVal() {
		return this.authId;
	}

	@Override
	public String toString() {
		return "AuthRes{" +
			", authId=" + authId +
			", resId=" + resId +
			"}";
	}
}
