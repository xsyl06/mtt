package com.ww.mtt.controller;

import com.ww.mtt.common.JsonData;
import com.ww.mtt.dto.DeptLevelDto;
import com.ww.mtt.param.DeptParam;
import com.ww.mtt.service.IDeptService;
import com.ww.mtt.service.ISysTreeService;
import com.ww.mtt.util.BeanValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sys/dept")
public class SysDeptController {
    private static final Logger logger = LoggerFactory.getLogger(SysDeptController.class);

    @Autowired
    private IDeptService deptService;

    @Autowired
    private ISysTreeService sysTreeService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public JsonData saveDept(@RequestBody DeptParam deptParam) {
        logger.info(deptParam.toString());
        BeanValidator.checkObject(deptParam);
        deptService.saveDept(deptParam);
        return JsonData.success();
    }

    @RequestMapping(value = "/tree",method = RequestMethod.GET)
    @ResponseBody
    public JsonData tree() {
        List<DeptLevelDto> doList = sysTreeService.deptTree();
        return JsonData.success(doList);
    }

    @RequestMapping("/update")
    @ResponseBody
    public JsonData update(@RequestBody DeptParam deptParam) {
        logger.info(deptParam.toString());
        BeanValidator.checkObject(deptParam);
        return null;
    }
}
