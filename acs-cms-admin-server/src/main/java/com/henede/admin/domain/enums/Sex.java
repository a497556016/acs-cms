package com.henede.admin.domain.enums;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IEnum;

public enum Sex implements IEnum {
	MAN("男","1"),
	WOMAN("女","0");

	private String name;
	private String value;
	
	private Sex(String name,String value) {
		this.name = name;
		this.value = value;
	}
	
	@Override
	public Serializable getValue() {
		// TODO Auto-generated method stub
		return this.value;
	}

}
