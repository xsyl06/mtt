package com.ww.mtt.service.impl;

import com.google.common.base.Preconditions;
import com.ww.mtt.bean.SysDept;
import com.ww.mtt.dao.SysDeptMapper;
import com.ww.mtt.exception.OwnExcetpein;
import com.ww.mtt.param.DeptParam;
import com.ww.mtt.service.IDeptService;
import com.ww.mtt.util.LevelUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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
        dept.setOperator("system"+"update"); //todo
        dept.setOperatorIp("127.0.0.1"); //todo
        dept.setOperatorTime(new Date());
        //插入表
        sysDeptMapper.insertSelective(dept);
    }

    /**
     * 更新部门
     * @param deptParam
     */
    @Override
    public void updateDept(DeptParam deptParam) {
        if(checkExist(deptParam.getParentId(),deptParam.getName(),deptParam.getId())){
            throw new OwnExcetpein("d01", "同一层级下存在相同名称的部门");
        }
        SysDept before = sysDeptMapper.selectByPrimaryKey(deptParam.getId());
        Preconditions.checkNotNull(before,"待更新部门不存在");
        SysDept after = SysDept.builder().id(deptParam.getId()).name(deptParam.getName()).parentId(deptParam.getParentId())
                .seq(deptParam.getSeq()).remark(deptParam.getRemark()).build();
        after.setLevel(LevelUtil.calculateLevel(getDeptLevel(deptParam.getParentId()),deptParam.getParentId()));
        after.setOperator("system"); //todo
        after.setOperatorIp("127.0.0.1"); //todo
        after.setOperatorTime(new Date());
        updateWithChildren(before,after);
    }

    @Transactional
    public void updateWithChildren(SysDept before, SysDept after) {
        String newLevelPrefix = after.getLevel();
        String oldLevelPrefix = before.getLevel();
        if (!after.getLevel().equals(before.getLevel())) {
            List<SysDept> deptList = sysDeptMapper.selectChildrenByLevel(oldLevelPrefix);
            if (CollectionUtils.isNotEmpty(deptList)) {
                for (SysDept dept : deptList) {
                    String level = dept.getLevel();
                    if(level.indexOf(oldLevelPrefix) == 0){
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        dept.setLevel(level);
                    }
                }
                sysDeptMapper.batchUpdateLevel(deptList);
            }
        }
        sysDeptMapper.updateByPrimaryKeySelective(after);
    }

    public boolean checkExist(Integer parentId, String deptName, Integer deptId) {
        return sysDeptMapper.countByNameAndParentId(parentId, deptName, deptId) > 0;
    }

    public String getDeptLevel(Integer deptId) {
        SysDept dept = sysDeptMapper.selectByPrimaryKey(deptId);
        if (dept == null) {
            return null;
        }
        return dept.getLevel();
    }
}
