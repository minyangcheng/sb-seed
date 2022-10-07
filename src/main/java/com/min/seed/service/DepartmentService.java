package com.min.seed.service;
import com.min.seed.entity.Department;
import com.min.seed.core.service.Service;


/**
 * Created by minych on 2022/10/07.
 */
public interface DepartmentService extends Service<Department> {

    Department getDepartmentDetail(Integer deptId);

}
