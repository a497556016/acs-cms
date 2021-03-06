package com.henede.admin.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableLogic;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author HeShaowei
 * @since 2017-12-26
 */
@TableName("sys_menu")
public class Menu extends Model<Menu> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	@TableField("p_id")
	private Integer pId;
	@TableField("res_id")
	private Integer resId;
	private String name;
	private String icon;
	private String description;
	private Integer sort;
	@TableField("delete_flag")
    @TableLogic
	private String deleteFlag;
	@TableField("create_time")
	private Date createTime;
	@TableField("create_by")
	private String createBy;
	@TableField("update_time")
	private Date updateTime;
	@TableField("update_by")
	private String updateBy;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public Integer getResId() {
		return resId;
	}

	public void setResId(Integer resId) {
		this.resId = resId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public static final String ID = "id";

	public static final String P_ID = "p_id";

	public static final String RES_ID = "res_id";

	public static final String NAME = "name";

	public static final String ICON = "icon";

	public static final String DESCRIPTION = "description";

	public static final String SORT = "sort";

	public static final String DELETE_FLAG = "delete_flag";

	public static final String CREATE_TIME = "create_time";

	public static final String CREATE_BY = "create_by";

	public static final String UPDATE_TIME = "update_time";

	public static final String UPDATE_BY = "update_by";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Menu{" +
			", id=" + id +
			", pId=" + pId +
			", resId=" + resId +
			", name=" + name +
			", icon=" + icon +
			", description=" + description +
				", sort=" + sort +
			", deleteFlag=" + deleteFlag +
			", createTime=" + createTime +
			", createBy=" + createBy +
			", updateTime=" + updateTime +
			", updateBy=" + updateBy +
			"}";
	}
}
