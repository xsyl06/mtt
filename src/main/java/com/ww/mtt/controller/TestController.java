package com.ww.mtt.controller;

import com.ww.mtt.bean.SysDept;
import com.ww.mtt.dao.SysDeptMapper;
import com.ww.mtt.exception.OwnExcetpein;
import com.ww.mtt.param.TestVo;
import com.ww.mtt.util.BeanValidator;
import com.ww.mtt.util.JsonUtil;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private SysDeptMapper sysDept;

    @RequestMapping("/demo")
    @ResponseBody
    public String demo(){
        return "hello";
    }

    @RequestMapping("/demo2")
    @ResponseBody
    public String demo2(){
        if(true){
            throw new OwnExcetpein("043", "参数错误");
        }
        return "hello";
    }

    @RequestMapping("/demo3")
    @ResponseBody
    public String demo2(TestVo t){
        try {
            Map<String, String> vires = BeanValidator.validateObject(t);
            if (MapUtils.isNotEmpty(vires)) {
                for (Map.Entry<String, String> entry : vires.entrySet()) {
                    logger.info("{}->{}", entry.getKey(),entry.getValue());
                }
            }
        } catch (Exception e) {

        }
        String b = "a";
        SysDept s = sysDept.selectByPrimaryKey(t.getA());
        logger.info(JsonUtil.obj2String(s));
        return "hello";
    }
}
