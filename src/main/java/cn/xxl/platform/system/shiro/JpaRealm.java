package cn.xxl.platform.system.shiro;

import cn.xxl.platform.system.dao.UserDao;
import cn.xxl.platform.system.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author xiaoxiaolong
 * @since 2023/5/15
 */
@Component
public class JpaRealm extends AuthenticatingRealm {// 认证的类，鉴权后面做
    @Resource
    private UserDao userDao;

    /**
     * 获得凭证匹配器
     * 用于校验前端输入的密码和数据库密码是不是对的上
     *
     * @return {@link CredentialsMatcher}
     */
    @Override
    public CredentialsMatcher getCredentialsMatcher() {
        HashedCredentialsMatcher md5 = new HashedCredentialsMatcher("MD5");
        md5.setHashIterations(10);// hash迭代十次
        return md5;
    }

    /**
     * 做得到身份验证信息
     *
     * @param token 令牌
     * @return {@link AuthenticationInfo}
     * @throws AuthenticationException 身份验证异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        User user = userDao.findByUsername(usernamePasswordToken.getUsername());
        String password = user.getPassword();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());// 这里的name是realm的name
        info.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
        return info;
    }
}
