package cn.xxl.platform.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import cn.xxl.platform.system.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

/**
 * 角色表
 *
 * @author xxl
 * @since 2023/07/04
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "system_role")
@Setter
@Getter
@Proxy(lazy=false)
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
    private String roleKey;

    /**
    * 排序
    */
    @Column
    private Integer sort;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    @ManyToMany
    @JoinTable(name="system_role_permission",
            joinColumns=
            @JoinColumn(name="role_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="permission_id", referencedColumnName="id")
    )
    private List<Permission> permissions;

    @Override
    public String toString() {
        return "Role(id=" + this.getId()
                + ", name=" + this.getName()
                + ", roleKey=" + this.getRoleKey()
                + ", sort=" + this.getSort()
                + ", users=" + this.getUsers()
                + ", permissions=" + this.getPermissions() + ")";
    }
}