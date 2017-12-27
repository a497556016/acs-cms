package com.henede.admin.controller;


import com.henede.admin.dto.MenuDto;
import com.henede.admin.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private IMenuService menuService;

    @RequestMapping("/selectAllMenus")
    public List<MenuDto> selectAllMenus(@RequestParam Map<String, Object> param){
        return menuService.selectAllMenus(param);
    }
}
