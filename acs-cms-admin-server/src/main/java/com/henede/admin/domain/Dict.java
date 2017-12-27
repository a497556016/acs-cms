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
@TableName("base_dict")
public class Dict extends Model<Dict> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	@TableField("p_id")
	private Integer pId;
	private String name;
	private String value;
	@TableField("delete_flag")
    @TableLogic
	private String deleteFlag;


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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public static final String ID = "id";

	public static final String P_ID = "p_id";

	public static final String NAME = "name";

	public static final String VALUE = "value";

	public static final String DELETE_FLAG = "delete_flag";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Dict{" +
			", id=" + id +
			", pId=" + pId +
			", name=" + name +
			", value=" + value +
			", deleteFlag=" + deleteFlag +
			"}";
	}
}
