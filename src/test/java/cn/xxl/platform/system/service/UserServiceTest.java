package cn.xxl.platform.system.service;

import cn.xxl.platform.system.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xiaoxiaolong
 * @since 2023/5/5
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    void getById() {
        User user = userService.findById(2L);
        System.out.println(user);
    }

    /**
     * 保存新增
     */
    @Test
    void save() {
        User user = new User();
        // user.setId(99);
        user.setUsername("admin");
        user.setPassword("123456");
        user.setGender(0);
        user.setIdCard("123456789012345678");
        user.setEmail("123@qq.com");
        user.setPhone("12345678901");
        user.setBirthday(LocalDateTime.now());
        user.setStatus(0);
        user.setIsDeleted(0);
        user.setLastLogin(LocalDateTime.now());
        userService.save(user);
    }

    @Test
    void login() {
        User user = new User();
        user.setUsername("张三");
        user.setPassword("123456");
        userService.login(user);
    }
}