package cn.xxl.platform.system.service.impl;

import cn.xxl.platform.system.dao.UserDao;
import cn.xxl.platform.system.entity.Permission;
import cn.xxl.platform.system.entity.Role;
import cn.xxl.platform.system.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaoxiaolong
 * @since 2023/5/29
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserDao userDao;

    @Override
    // 这里需要在一个事务中完成，否则会报懒加载异常，提示no session，会话已关闭
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        List<Role> roles = user.getRoles();
        List<Permission> permissions = roles
                .stream()
                .flatMap(role -> role
                        .getPermissions()
                        .stream())
                .distinct()
                .collect(Collectors.toList());
        user.setAuthorities(permissions);
        return user;
    }
}
