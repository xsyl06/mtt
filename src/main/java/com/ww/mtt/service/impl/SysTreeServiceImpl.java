package com.ww.mtt.service.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.ww.mtt.bean.SysDept;
import com.ww.mtt.dao.SysDeptMapper;
import com.ww.mtt.dto.DeptLevelDto;
import com.ww.mtt.service.ISysTreeService;
import com.ww.mtt.util.LevelUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
@Service
public class SysTreeServiceImpl implements ISysTreeService {
    private static final Logger logger = LoggerFactory.getLogger(SysTreeServiceImpl.class);
    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
    public List<DeptLevelDto> deptTree() {
        List<DeptLevelDto> dtoList = Lists.newArrayList();
        List<SysDept> deptList = sysDeptMapper.getAllDept();
        for(SysDept sysDept:deptList){
            DeptLevelDto dto = DeptLevelDto.adapt(sysDept);
            dtoList.add(dto);
        }
        return deptListToTree(dtoList);
    }

    public List<DeptLevelDto> deptListToTree(List<DeptLevelDto> dtoList) {
        if(CollectionUtils.isEmpty(dtoList)){
            return Lists.newArrayList();
        }
        //level --> [dept1,dept2,dept3...]
        //将同一等级下的部门放入特殊的map，key为等级id，value为部门对象
        Multimap<String, DeptLevelDto> levelDtoMultimap = ArrayListMultimap.create();
        List<DeptLevelDto> rootList = Lists.newArrayList();
        for (DeptLevelDto dto : dtoList) {
            levelDtoMultimap.put(dto.getLevel(), dto);
            if (LevelUtil.ROOT.equals(dto.getLevel())) {
                rootList.add(dto);
            }
        }

        //对rootList根部门列表进行seq排序
        Collections.sort(rootList, new Comparator<DeptLevelDto>() {
            @Override
            public int compare(DeptLevelDto o1, DeptLevelDto o2) {
                return o1.getSeq()-o2.getSeq();
            }
        });
        transfromDeptTree(rootList,LevelUtil.ROOT,levelDtoMultimap);
        return rootList;

    }

    public void transfromDeptTree(List<DeptLevelDto> deptLevelList, String level, Multimap<String,DeptLevelDto> multimap) {
        for (int i = 0; i < deptLevelList.size(); i++) {
            //遍历list里的元素
            DeptLevelDto deptLevelDto = deptLevelList.get(i);

            //处理当前层级的数据
            String nextLevel = LevelUtil.calculateLevel(level, deptLevelDto.getId());

            //处理下一层
            List<DeptLevelDto> tempDeptList = (List<DeptLevelDto>) multimap.get(nextLevel);

            if(CollectionUtils.isNotEmpty(tempDeptList)){
                //排序
                Collections.sort(tempDeptList,deptSeqComparator);
                //设置下一层部门
                deptLevelDto.setDeptList(tempDeptList);
                //进入到下一层
                transfromDeptTree(tempDeptList,nextLevel,multimap);
            }
        }


    }

    public Comparator<DeptLevelDto> deptSeqComparator = new Comparator<DeptLevelDto>() {
        @Override
        public int compare(DeptLevelDto o1, DeptLevelDto o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };
}
