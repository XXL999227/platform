package cn.xxl.platform.system.controller;

import cn.xxl.platform.system.entity.Result;
import cn.xxl.platform.system.entity.User;
import cn.xxl.platform.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author xiaoxiaolong
 * @since 2023/5/5
 */
@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/{id}")
    Result<User> getById(@PathVariable Long id) {
        log.info("查询用户信息，id：{}", id);
        return Result.ok(userService.findById(id));
    }

    @PostMapping("/save")
    Result<Void> save(@RequestBody User user) {
        userService.save(user);
        return Result.ok();
    }

    @PutMapping("/update")
    Result<Void> update(@RequestBody User user) {
        userService.updateById(user);
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    Result<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return Result.ok();
    }
}
