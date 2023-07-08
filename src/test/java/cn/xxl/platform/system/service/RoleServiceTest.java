package cn.xxl.platform.system.service;

import cn.xxl.platform.system.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Slf4j
class RoleServiceTest {

    @Resource
    private RoleService roleService;

    @Test
    void save() {
        Role role = new Role();
        role.setName("管理员");
        role.setRoleKey("ROLE_ADMIN");
        role.setSort(1);
        role.setIsDeleted(0);
        roleService.save(role);
    }

    @Test
    void findById() {
        Role role = roleService.findById(1L);
        // log.info(role.toString());
    }
}