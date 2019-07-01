package com.ww.mtt.dao;

import com.ww.mtt.bean.SysDept;
import org.springframework.stereotype.Repository;

@Repository
public interface SysDeptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);
}