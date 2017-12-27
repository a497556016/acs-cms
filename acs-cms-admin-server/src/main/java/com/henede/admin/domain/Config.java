package com.henede.admin.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
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
@TableName("sys_config")
public class Config extends Model<Config> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String name;
	@TableField("p_name")
	private String pName;
	private String value;
	private String description;
	@TableField("delete_flag")
    @TableLogic
	private String deleteFlag;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

	public static final String ID = "id";

	public static final String NAME = "name";

	public static final String P_NAME = "p_name";

	public static final String VALUE = "value";

	public static final String DESCRIPTION = "description";

	public static final String DELETE_FLAG = "delete_flag";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Config{" +
			", id=" + id +
			", name=" + name +
			", pName=" + pName +
			", value=" + value +
			", description=" + description +
			", deleteFlag=" + deleteFlag +
			"}";
	}
}
