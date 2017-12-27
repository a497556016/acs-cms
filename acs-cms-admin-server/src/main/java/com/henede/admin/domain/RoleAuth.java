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
@TableName("sys_role_auth")
public class RoleAuth extends Model<RoleAuth> {

    private static final long serialVersionUID = 1L;

    @TableId("role_id")
	private Integer roleId;
	@TableField("auth_id")
	private Integer authId;


	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getAuthId() {
		return authId;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
	}

	public static final String ROLE_ID = "role_id";

	public static final String AUTH_ID = "auth_id";

	@Override
	protected Serializable pkVal() {
		return this.roleId;
	}

	@Override
	public String toString() {
		return "RoleAuth{" +
			", roleId=" + roleId +
			", authId=" + authId +
			"}";
	}
}
