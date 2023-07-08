package cn.xxl.platform.system.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Proxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * 用户表
 *
 * @author xiaoxiaolong
 * @since 2023/04/13
 */
@EqualsAndHashCode(callSuper = true)// 是否调用父类的equals和hashCode方法
@Entity
@Table(name = "system_user")
@Data
@Proxy(lazy=false)
public class User extends BaseEntity implements UserDetails {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// 自增长策略,让持久层的提供者，也就是数据库，自己去生成主键
    private Long id;

    /**
     * 用户名
     */
    @Column
    private String username;

    /**
     * 密码
     */
    @Column
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 性别 0-男 1-女
     */
    private Integer gender;

    /**
     * 身份证号
     */
    @Column
    private String idCard;


    /**
     * 邮箱
     */
    @Column
    private String email;


    /**
     * 电话
     */
    @Column
    private String phone;


    /**
     * 生日
     */
    @Column
    private LocalDateTime birthday;

    /**
     * 年龄
     */
    @Column
    private Integer age;

    /**
     * 状态 1-正常 2-禁用
     */
    @Column
    private Integer status;

    /**
     * 上次登录时间
     */
    @Column
    private LocalDateTime lastLogin;

    @ManyToMany
    @JoinTable(name = "system_user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<Role> roles;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", idCard='" + idCard + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", birthday=" + birthday +
                ", age=" + age +
                ", status=" + status +
                ", lastLogin=" + lastLogin +
                "} " + super.toString();
    }

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}