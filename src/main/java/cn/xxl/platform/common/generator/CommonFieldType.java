package cn.xxl.platform.common.generator;

import java.util.Optional;

/**
 * 常见字段类型
 *
 * @author xxl
 * @since 2023/06/28
 */
public enum CommonFieldType {
    VARCHAR("varchar", "String"),
    INT("int", "Integer"),
    BIGINT("bigint", "Long"),
    TINYINT("tinyint", "Integer"),
    DATETIME("datetime", "LocalDateTime"),
    DATE("date", "LocalDateTime"),
    TIMESTAMP("timestamp", "LocalDateTime"),
    TEXT("text", "String"),
    LONGTEXT("longtext", "String"),
    DECIMAL("decimal", "BigDecimal");

    private String dbType;
    private String javaType;

    CommonFieldType(String dbType, String java) {
        this.dbType = dbType;
        this.javaType = java;
    }
    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public static Optional<CommonFieldType> of(String dbType) {
        for (CommonFieldType value : CommonFieldType.values()) {
            if (value.getDbType().equals(dbType)) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }
}
