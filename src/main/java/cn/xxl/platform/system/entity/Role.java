package cn.xxl.platform.system.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

import cn.xxl.platform.system.entity.BaseEntity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "system_role")
@Data
public class Role extends BaseEntity {

    /**
    * 主键
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
    * 名称
    */
    @Column
    private String name;

    /**
    * 代表权限的字符串
    */
    @Column
    private String key;

    /**
    * 排序
    */
    @Column
    private Integer sort;


}