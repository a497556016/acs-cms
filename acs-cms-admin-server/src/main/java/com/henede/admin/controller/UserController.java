package com.henede.admin.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Maps;
import com.henede.admin.domain.User;
import com.henede.admin.service.IUserService;
import com.henede.auth.bean.UserDetails;
import com.henede.auth.exception.PasswordNotMatchsException;
import com.henede.auth.exception.UsernameNotFoundException;
import com.henede.auth.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private UserManager userManager;

    @RequestMapping("/test")
    public Object test() {


        return userService.selectList(new EntityWrapper<>());
    }

    @RequestMapping("/login")
    public Object login(String username,String password) {
        Map<String, Object> re = Maps.newHashMap();
        re.put("code", 0);
        try {
//            User entity = new User();
//            entity.setUserName(username);
//            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//            entity.setPassword(passwordEncoder.encode("123456"));
//            userService.insert(entity);

            UserDetails userDetails = userManager.login(username, password);

            re.put("msg", "登录成功！");
            re.put("code", 1);
            re.put("data", userDetails);
        } catch (UsernameNotFoundException e) {
            re.put("msg", e.getMessage());
        } catch (PasswordNotMatchsException e) {
            re.put("msg", e.getMessage());
        }
        return re;
    }

    @RequestMapping("/refreshToken")
    public Object refreshToken(String refreshToken) {
        Map<String, Object> re = Maps.newHashMap();
        re.put("data", userManager.refreshToken(refreshToken));
        re.put("code", 1);
        return re;
    }
}
