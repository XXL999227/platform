package cn.xxl.platform.system.dao;

import cn.xxl.platform.system.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author xiaoxiaolong
 * @since 2023/4/16
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserDaoTest {

    @Resource
    private UserDao userDao;

    /**
     * 保存新增
     */
    @Test
     void save() {
        User user = new User();
        // user.setId(99);
        user.setUsername("xiaoxiaolong");
        user.setPassword("123456");
        user.setGender(0);
        user.setIdCard("123456789012345678");
        user.setEmail("123@qq.com");
        user.setPhone("12345678901");
        user.setBirthday(LocalDateTime.now());
        user.setStatus(0);
        user.setLastLogin(LocalDateTime.now());
        userDao.save(user);
    }

    /**
     * 通过id查找
     */
    @Test
    void getById() {
        Optional<User> user = userDao.findById(2);
        boolean present = user.isPresent();
        System.out.println(present);
        user.ifPresent(System.out::println);

        System.out.println(user);
    }

    /**
     * 更新通过id
     */
    @Test
    void updateById(){
        Optional<User> user = userDao.findById(2);
        user.ifPresent(user1 ->{
            user1.setUsername("xxl2");
            userDao.save(user1);
        });
        user.ifPresent(System.out::println);
    }

    /**
     * 物理删除通过id
     */
    @Test
    // @Transactional
    void deleteById(){
        userDao.deleteById(5);
        Optional<User> user = userDao.findById(5);
        Assertions.assertTrue(!user.isPresent());
    }

    /**
     * 逻辑删除通过id
     */
    @Test
    void logicDeleteById(){
        userDao.logicDeleteById(6);
        Assertions.assertFalse(userDao.findById(6).isPresent());
    }
}