package com.ww.mtt.service;

import com.ww.mtt.param.DeptParam;

public interface IDeptService {
    void saveDept(DeptParam sysDept);

    void updateDept(DeptParam deptParam);
}
