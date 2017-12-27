package com.henede.admin.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Maps;
import com.henede.admin.domain.Config;
import com.henede.admin.dto.MenuDto;
import com.henede.admin.service.IConfigService;
import com.henede.admin.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author HeShaowei
 * @since 2017-12-26
 */
@RestController
@RequestMapping("/config")
public class ConfigController {
    @Autowired
    private IConfigService configService;
    @Autowired
    private IMenuService menuService;

    @RequestMapping("/getSysConfig")
    @Cacheable(value="sys-config")
    public Object getSysConfig() {
        Map<String, Object> reMap = Maps.newHashMap();
        EntityWrapper<Config> wrapper = new EntityWrapper<>();
        wrapper.isNull(Config.P_NAME);
        List<Config> configs = configService.selectList(wrapper);
        for (Config config : configs) {
            EntityWrapper<Config> c_wrapper = new EntityWrapper<>();
            c_wrapper.eq(Config.P_NAME, config.getName());
            List<Config> c_configs = configService.selectList(c_wrapper);
            Map<String, String> c_map = Maps.newHashMap();
            for(Config c_config : c_configs) {
                c_map.put(c_config.getName(), c_config.getValue());
            }
            reMap.put(config.getName(), c_map);
        }
        //加载菜单
        List<MenuDto> menuDtos = menuService.selectAllMenus(Maps.newHashMap());
        reMap.put("menu", menuDtos);
        return reMap;
    }
}
