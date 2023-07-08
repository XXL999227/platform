package cn.xxl.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * 启动类
 *
 * @author xiaoxiaolong
 * @since 2023/04/03
 */
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class PlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlatformApplication.class, args);
    }

}
