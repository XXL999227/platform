package cn.xxl.platform.common.generator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class CodeGeneratorTest {

    @Resource
    private CodeGenerator codeGenerator;

    @Test
    void generateEntity() {
        String path = "src/main/java/cn/xxl/platform/system/";
        String entityPackage = "cn.xxl.platform.system";
        codeGenerator.generateEntity(path, entityPackage, "system_role", "Role");
    }

    @Test
    void generate() {
        String basePath = "cn/xxl/platform/system";
        codeGenerator.generate(basePath, "system_role", "Role");
    }
}