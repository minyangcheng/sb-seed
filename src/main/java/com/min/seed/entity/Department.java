package com.min.seed.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_dept")
public class Department {
    /**
     * ID
     */
    @Id
    @Column(name = "dept_id")
    private Long deptId;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门级别
     */
    private Integer level;

    /**
     * 创建日期
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取ID
     *
     * @return dept_id - ID
     */
    public Long getDeptId() {
        return deptId;
    }

    /**
     * 设置ID
     *
     * @param deptId ID
     */
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    /**
     * 获取部门名称
     *
     * @return name - 部门名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置部门名称
     *
     * @param name 部门名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取部门级别
     *
     * @return level - 部门级别
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置部门级别
     *
     * @param level 部门级别
     */
    public void setLevel(Integer level) {
        this.level = level;
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
}