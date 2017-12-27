package com.henede.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.henede.admin.domain.Role;
import com.henede.admin.domain.User;
import com.henede.admin.mapper.UserMapper;
import com.henede.admin.service.IResourceService;
import com.henede.admin.service.IRoleService;
import com.henede.admin.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.henede.auth.bean.SimpleGrantedAuthority;
import com.henede.auth.bean.UserDetails;
import com.henede.auth.exception.UsernameNotFoundException;
import com.henede.auth.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HeShaowei
 * @since 2017-12-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService,UserDetailsService {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IResourceService resourceService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq(User.USER_NAME, username);
        User user = this.selectOne(wrapper);
        if (null == user) {
            throw new UsernameNotFoundException("用户名不存在！");
        }
        //根据用户获取用户角色
        List<Role> roles = roleService.selectUserRole(user.getId());
        //定义权限集合
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        //添加权限到集合中
        for (Role role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        UserDetails userDetail =
                new UserDetails(username, user.getPassword(), grantedAuthorities);
        return userDetail;
    }

    @Override
    public UserDetails getUserByUsername(String username) throws UsernameNotFoundException {
        return this.loadUserByUsername(username);
    }

    @Override
    public Map<String, String> loadAuthResource() {
        List<Map<String, String>> list = resourceService.selectResourceAuths();
        Map<String, String> map = list.stream().collect(Collectors.toMap(m -> m.get("name"), m -> m.get("url")));
        return map;
    }

    @Override
    public Map<String, String> getAuthResource() {
        return this.loadAuthResource();

    }
}
