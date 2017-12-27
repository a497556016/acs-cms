package com.henede.admin.service.impl;

import com.google.common.collect.Lists;
import com.henede.admin.domain.Menu;
import com.henede.admin.dto.MenuDto;
import com.henede.admin.mapper.MenuMapper;
import com.henede.admin.service.IMenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HeShaowei
 * @since 2017-12-26
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Override
    public List<MenuDto> selectAllMenus(Map<String, Object> param) {
        List<MenuDto> menus = this.baseMapper.selectAllMenus(param);
        this.loadChildrenMenus(menus, param);
        return menus;
    }

    private void loadChildrenMenus(List<MenuDto> menus,Map<String, Object> param) {
        menus.iterator().forEachRemaining(menu ->{
            param.put("pId", menu.getId());
            List<MenuDto> children = this.baseMapper.selectAllMenus(param);
            if(null!=children) {
                menu.setChildren(children);
                this.loadChildrenMenus(children, param);
            }else {
                menu.setChildren(Lists.newArrayList());
            }
        });
    }
}
