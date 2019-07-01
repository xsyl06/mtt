package com.ww.mtt.controller;

import com.ww.mtt.common.JsonData;
import com.ww.mtt.param.DeptParam;
import com.ww.mtt.service.IDeptService;
import com.ww.mtt.util.BeanValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/sys/dept")
public class SysDeptController {
    @Autowired
    private IDeptService deptService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public JsonData saveDept(DeptParam deptParam) {
        Map map = BeanValidator.validateObject(deptParam);

        deptService.saveDept(deptParam);
        return JsonData.success();
    }
}
