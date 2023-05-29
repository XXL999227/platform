package cn.xxl.platform.system.service.impl;

import cn.xxl.platform.system.dao.UserDao;
import cn.xxl.platform.system.entity.User;
import cn.xxl.platform.system.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xiaoxiaolong
 * @since 2023/5/5
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDao, User> implements UserService {
    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private BCryptPasswordEncoder encoder;

    @Override
    public void save(User entity) {
        // String salt = new SecureRandomNumberGenerator().nextBytes().toHex();//生成一个随机的盐值
        // // 加密
        // SimpleHash hash = new SimpleHash("MD5", entity.getPassword(), salt, 10);
        // entity.setPassword(hash.toHex());
        // entity.setSalt(salt);
        // super.save(entity);

        entity.setPassword(encoder.encode(entity.getPassword()));
        super.save(entity);
    }

    @Override
    public void login(User user) {
        // 不用shiro做认证了
        // Subject subject = SecurityUtils.getSubject();
        // UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        // try {
        //     //login方法会调用Realm中的两个方法
        //     subject.login(usernamePasswordToken);
        // }catch (AuthenticationException e){
        //     throw new RuntimeException(e);
        // }

        // 改用spring-security
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        authenticationManager.authenticate(token);
    }
}
