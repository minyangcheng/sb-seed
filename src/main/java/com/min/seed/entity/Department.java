package com.min.seed.entity;

import java.util.Date;
import java.util.List;
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
     * 该部门下员工
     */
    private List<User> persons;

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

    public List<User> getPersons() {
        return persons;
    }

    public void setPersons(List<User> persons) {
        this.persons = persons;
    }
}