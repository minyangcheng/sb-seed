package com.min.seed.service.impl;

import com.min.seed.dao.DepartmentMapper;
import com.min.seed.entity.Department;
import com.min.seed.service.DepartmentService;
import com.min.seed.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by minych on 2022/10/07.
 */
@Service
@Transactional
public class DepartmentServiceImpl extends AbstractService<Department> implements DepartmentService {

    @Resource
    private DepartmentMapper sysDeptMapper;

}
