package com.henede.admin.mapper;

import com.henede.admin.domain.Resource;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author HeShaowei
 * @since 2017-12-26
 */
public interface ResourceMapper extends BaseMapper<Resource> {

    List<Map<String, String>> selectResourceAuths();

}