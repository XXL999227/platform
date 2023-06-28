package cn.xxl.platform.common.generator;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * 代表生成的实体类中的一个字段
 */
@Data
@AllArgsConstructor
public class CommonField {
    private String name;
    private CommonFieldType type;
    private String comment;
}
