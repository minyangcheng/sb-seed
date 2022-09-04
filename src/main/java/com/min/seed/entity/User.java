package com.min.seed.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.min.seed.core.validation.ValidGroup;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Table(name = "sys_user")
public class User {
    /**
     * ID
     */
    @Id
    @Column(name = "user_id")
    private Long userId;

    /**
     * 用户名
     */
    @NotBlank(groups = ValidGroup.Crud.Create.class)
    private String username;

    /**
     * 昵称
     */
    @Column(name = "nick_name")
    @NotBlank
    private String nickName;

    /**
     * 性别
     */
    @NotBlank
    private String gender;

    /**
     * 手机号码
     */
    @NotBlank
    private String phone;

    /**
     * 邮箱
     */
    @NotBlank
    private String email;

    /**
     * 密码
     */
    @NotBlank
    private String password;

    /**
     * 是否为admin账号
     */
    @Column(name = "is_admin")
    private Integer isAdmin;

    /**
     * 状态：1启用、0禁用
     */
    private Integer enabled;

    /**
     * 修改密码的时间
     */
    @Column(name = "pwd_reset_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date pwdResetTime;

    /**
     * 创建日期
     */
    @Column(name = "create_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 获取ID
     *
     * @return user_id - ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置ID
     *
     * @param userId ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取昵称
     *
     * @return nick_name - 昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置昵称
     *
     * @param nickName 昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取性别
     *
     * @return gender - 性别
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置性别
     *
     * @param gender 性别
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 获取手机号码
     *
     * @return phone - 手机号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号码
     *
     * @param phone 手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取是否为admin账号
     *
     * @return is_admin - 是否为admin账号
     */
    public Integer getIsAdmin() {
        return isAdmin;
    }

    /**
     * 设置是否为admin账号
     *
     * @param isAdmin 是否为admin账号
     */
    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * 获取状态：1启用、0禁用
     *
     * @return enabled - 状态：1启用、0禁用
     */
    public Integer getEnabled() {
        return enabled;
    }

    /**
     * 设置状态：1启用、0禁用
     *
     * @param enabled 状态：1启用、0禁用
     */
    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取修改密码的时间
     *
     * @return pwd_reset_time - 修改密码的时间
     */
    public Date getPwdResetTime() {
        return pwdResetTime;
    }

    /**
     * 设置修改密码的时间
     *
     * @param pwdResetTime 修改密码的时间
     */
    public void setPwdResetTime(Date pwdResetTime) {
        this.pwdResetTime = pwdResetTime;
    }

    /**
     * 获取创建日期
     *
     * @return create_time - 创建日期
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建日期
     *
     * @param createTime 创建日期
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", nickName='" + nickName + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                ", enabled=" + enabled +
                ", pwdResetTime=" + pwdResetTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}