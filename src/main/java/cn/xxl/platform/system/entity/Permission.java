package cn.xxl.platform.system.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import cn.xxl.platform.system.entity.BaseEntity;
import org.hibernate.annotations.Proxy;
import org.springframework.security.core.GrantedAuthority;

/**
 * 权限表
 *
 * @author xxl
 * @since 2023/07/04
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "system_permission")
@Data
@Proxy(lazy=false)
public class Permission extends BaseEntity implements GrantedAuthority {

    /**
    * 主键
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
    * 权限字符串
    */
    @Column
    private String name;

    @ManyToMany(mappedBy = "permissions")
    private List<Role> roles;

    @Override
    public String getAuthority() {
        return name;
    }
}