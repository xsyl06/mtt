package com.ww.mtt.service.impl;

import com.ww.mtt.bean.SysDept;
import com.ww.mtt.dao.SysDeptMapper;
import com.ww.mtt.exception.OwnExcetpein;
import com.ww.mtt.param.DeptParam;
import com.ww.mtt.service.IDeptService;
import com.ww.mtt.util.LevelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeptServiceImpl implements IDeptService {
    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
    public void saveDept(DeptParam sysDept) {
        if(checkExist(sysDept.getParentId(),sysDept.getName(),sysDept.getId())){
            throw new OwnExcetpein("d01", "同一层级下存在相同名称的部门");
        }
        SysDept dept = SysDept.builder().name(sysDept.getName()).parentId(sysDept.getParentId())
                .seq(sysDept.getSeq()).remark(sysDept.getRemark()).build();
        dept.setLevel(LevelUtil.calculateLevel(getDeptLevel(sysDept.getParentId()),sysDept.getParentId()));
        dept.setOperator("system"); //todo
        dept.setOperatorIp("127.0.0.1"); //todo
        //插入表
        sysDeptMapper.insertSelective(dept);
    }

    public boolean checkExist(Integer parentId, String deptName, Integer deptId) {
        //todo
        return true;
    }

    public String getDeptLevel(Integer deptId) {
        SysDept dept = sysDeptMapper.selectByPrimaryKey(deptId);
        if (dept == null) {
            return null;
        }
        return dept.getLevel();
    }
}
