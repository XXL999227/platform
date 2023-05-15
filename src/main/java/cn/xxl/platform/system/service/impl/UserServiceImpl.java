package cn.xxl.platform.system.service.impl;

import cn.xxl.platform.system.dao.UserDao;
import cn.xxl.platform.system.entity.User;
import cn.xxl.platform.system.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

/**
 * @author xiaoxiaolong
 * @since 2023/5/5
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDao, User> implements UserService {
    @Override
    public void save(User entity) {
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();//生成一个随机的盐值
        // 加密
        SimpleHash hash = new SimpleHash("MD5", entity.getPassword(), salt, 10);
        entity.setPassword(hash.toHex());
        entity.setSalt(salt);
        super.save(entity);
    }

    @Override
    public void login(User user){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            //login方法会调用Realm中的两个方法
            subject.login(usernamePasswordToken);
        }catch (AuthenticationException e){
            throw new RuntimeException(e);
        }
    }
}
