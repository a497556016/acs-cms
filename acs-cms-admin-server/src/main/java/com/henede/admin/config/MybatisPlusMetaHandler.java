package com.henede.admin.config;

import java.sql.Timestamp;
import java.util.Map;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.google.common.collect.Maps;

@Component
public class MybatisPlusMetaHandler extends MetaObjectHandler {
	
	private static final ThreadLocal<Map<String, Object>> FIELD_VAL_MAP = new ThreadLocal<>();
	
	public static void setFieldVal(String key,Object val) {
		Map<String, Object> map = FIELD_VAL_MAP.get();
		if(null==map) {
			map = Maps.newHashMap();
			FIELD_VAL_MAP.set(map);
		}
		map.put(key, val);
	}
	
	private void setFieldVal(MetaObject metaObject) {
		Map<String, Object> map = FIELD_VAL_MAP.get();
		if(null!=map) {
			map.forEach((k,v) -> super.setFieldValByName(k, v, metaObject));
		}
	}

	@Override
	public void insertFill(MetaObject metaObject) {
		this.setFieldVal(metaObject);
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		this.setFieldValByName("updateTime", new Timestamp(System.currentTimeMillis()), metaObject);
	}

}
