package com.five.year.fiveyearblog.entity;

import com.five.year.fiveyearblog.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 *
 * @Author zhaoke
 *
 * @Date 2019/01/22
 *
 * @Description 博客用户实体
 */
@Setter
@Getter
public class BlogUser extends BaseEntity implements UserDetails {

    private static final Short ENABLE_FALSE = 0;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 手机号
     */
    private String userPhone;

    /**
     * 邮箱
     */
    private String userEmail;

    /**
     * 启用(0 未启用  1启用)
     */
    private Short enable = 1;

    /**
     * 用户的角色
     * @return 角色组
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.userPassword;
    }

    @Override
    public String getUsername() {
        return this.userName;
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
        return !this.enable.equals(ENABLE_FALSE);
    }
}