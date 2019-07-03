package com.ww.mtt.service;

import com.ww.mtt.dto.DeptLevelDto;

import java.util.List;

public interface ISysTreeService {
    /**
     * 树形列表公共方法
     * @return
     */
    List<DeptLevelDto> deptTree();
}
