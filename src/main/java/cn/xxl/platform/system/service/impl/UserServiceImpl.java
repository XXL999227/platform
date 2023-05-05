package cn.xxl.platform.system.service.impl;

import cn.xxl.platform.system.dao.UserDao;
import cn.xxl.platform.system.entity.User;
import cn.xxl.platform.system.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author xiaoxiaolong
 * @since 2023/5/5
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDao, User> implements UserService {
}
