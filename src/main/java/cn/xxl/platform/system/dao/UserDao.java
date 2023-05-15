package cn.xxl.platform.system.dao;

import cn.xxl.platform.system.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author xiaoxiaolong
 * @since 2023/4/16
 */
@Repository
public interface UserDao extends BaseDao<User> {

    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return {@link User}
     */
    User findByUsername(String username);
}
