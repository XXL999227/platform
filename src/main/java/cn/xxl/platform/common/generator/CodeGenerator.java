package cn.xxl.platform.common.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 用于生成实体、Controller、Service、代码的类
 */
@Service
public final class CodeGenerator {
    private final JdbcTemplate jdbcTemplate;

    public CodeGenerator(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 下划线转驼峰aa_bb_cc -> aaBbCc
     */
    private String underlineToCamel(String str) {
        StringBuilder sb = new StringBuilder();
        String[] split = str.split("_");
        for (String s : split) {
            if (sb.length() == 0) {
                sb.append(s.toLowerCase());
            } else {
                sb.append(s.substring(0, 1).toUpperCase()).append(s.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }

    /**
     * 生成实体
     *
     * @param entityPath  实体路径
     * @param basePackage 基本包
     * @param tableName   表名
     * @param className   类名
     */
    public void generateEntity(String entityPath, String basePackage, String tableName, String className) {
        String sql = "select column_name, column_type, column_comment from information_schema.columns" +
                " where table_name = '" + tableName + "' and table_schema = (select database())";
        List<CommonField> fields = jdbcTemplate.queryForList(sql)
                .stream()
                .map(this::buildFieldFromMap)
                .filter(field ->
                        !Arrays.asList("id", "createdTime", "createdBy", "updatedTime", "updatedBy", "isDeleted", "remark")
                                .contains(field.getName()))
                .collect(Collectors.toList());

        // 通过freeMarker生成实体类
        Map<String, Object> params = new HashMap<>();
        params.put("fields", fields);
        params.put("className", className);
        params.put("tableName", tableName);
        params.put("basePackage", basePackage);

        generateClass(entityPath, "entity.java.ftl", className, params);
    }

    /**
     * 从map构建字段
     *
     * @param map 地图
     * @return {@link CommonField}
     */
    private CommonField buildFieldFromMap(Map<String, Object> map) {
        String columnName = map.get("column_name").toString();
        String columnType = map.get("column_type").toString();
        String columnComment = map.get("column_comment").toString();

        // 获取字段类型枚举
        CommonFieldType type = CommonFieldType.of(columnType).orElse(CommonFieldType.VARCHAR);

        // 将字段名转换为驼峰命名
        columnName = underlineToCamel(columnName);

        return new CommonField(columnName, type, columnComment);
    }

    /**
     * 生成类
     *
     * @param path         路径
     * @param templateName 模板名称
     * @param className    类名
     * @param params       参数个数
     */
    private void generateClass(String path, String templateName, String className, Map<String, Object> params) {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassLoaderForTemplateLoading(CodeGenerator.class.getClassLoader(), "templates");
        try {
            Template template = configuration.getTemplate(templateName);
            template.process(params, Files.newBufferedWriter(Paths.get(path + className + ".java")));
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 将path转换为包名
     */
    private String pathToPackage(String path) {
        return path.replace("/", ".").replace("\\", ".");
    }

    /**
     * 生成所有代码
     *
     * @param basePath  基本路径
     * @param tableName 表名
     * @param className 类名
     */
    public void generate(String basePath, String tableName, String className) {
        String entityPath = "src/main/java/" + basePath + "/entity/";
        String basePackage = pathToPackage(basePath);
        generateEntity(entityPath, basePackage, tableName, className);

        String controllerPath = "src/main/java/" + basePath + "/controller/";
        generateController(controllerPath, basePackage, className);

        String servicePath = "src/main/java/" + basePath + "/service/";
        generateService(servicePath, basePackage, className);

        String serviceImplPath = "src/main/java/" + basePath + "/service/impl/";
        generateServiceImpl(serviceImplPath, basePackage, className);

        String daoPath = "src/main/java/" + basePath + "/dao/";
        generateDao(daoPath, basePackage, className);
    }

    private void generateController(String controllerPath, String basePackage, String className) {
        // 通过freeMarker生成类
        Map<String, Object> params = new HashMap<>();
        params.put("className", className);
        params.put("basePackage", basePackage);

        generateClass(controllerPath, "controller.java.ftl", className + "Controller", params);
    }

    private void generateDao(String daoPath, String basePackage, String className) {
        // 通过freeMarker生成类
        Map<String, Object> params = new HashMap<>();
        params.put("className", className);
        params.put("basePackage", basePackage);

        generateClass(daoPath, "dao.java.ftl", className + "Dao", params);
    }

    private void generateServiceImpl(String serviceImplPath, String basePackage, String className) {
        // 通过freeMarker生成类
        Map<String, Object> params = new HashMap<>();
        params.put("className", className);
        params.put("basePackage", basePackage);

        generateClass(serviceImplPath, "serviceImpl.java.ftl", className + "ServiceImpl", params);
    }

    private void generateService(String servicePath, String basePackage, String className) {
        // 通过freeMarker生成类
        Map<String, Object> params = new HashMap<>();
        params.put("className", className);
        params.put("basePackage", basePackage);

        generateClass(servicePath, "service.java.ftl", className + "Service", params);
    }
}
