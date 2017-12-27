package com.henede.admin.service;

import com.henede.admin.domain.Resource;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author HeShaowei
 * @since 2017-12-26
 */
public interface IResourceService extends IService<Resource> {
    List<Map<String, String>> selectResourceAuths();
}
