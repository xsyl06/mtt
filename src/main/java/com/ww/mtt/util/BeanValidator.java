package com.ww.mtt.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ww.mtt.exception.OwnExcetpein;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

/**
 * 通用bean参数校验类
 */
public class BeanValidator {
    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private static final Logger logger = LoggerFactory.getLogger(BeanValidator.class);


    /**
     * 校验类
     * @param t
     * @param groups
     * @param <T>
     * @return
     */
    private static <T> Map<String, String> validate(T t, Class... groups) {
        Validator validator = validatorFactory.getValidator();
        //进行校验
        Set validateResult = validator.validate(t, groups);
        //判断校验返回结果是否有值
        if(validateResult.isEmpty()){
            return Collections.emptyMap();
        }else{
            //遍历校验结果，放入返回参数中
            //创建空的链表
            LinkedHashMap errors = Maps.newLinkedHashMap();
            //获取迭代器
            for (Object o : validateResult) {
                ConstraintViolation violation = (ConstraintViolation) o;
                errors.put(violation.getPropertyPath().toString(), violation.getMessage());
            }
            return errors;
        }
    }

    /**
     * 校验列表
     * @param collection 集合
     * @return
     */
    private static Map<String,String> validateList(Collection<?> collection){
        Preconditions.checkNotNull(collection);
        Map<String, String> errors;
        Iterator iterator = collection.iterator();
        do {
            if(!iterator.hasNext()){
                return Collections.emptyMap();
            }
            Object object = iterator.next();
            errors = validate(object, new Class[0]);
        }while (errors.isEmpty());
        return errors;
    }

    /**
     * 统一对外提供验证方法
     * @param first
     * @param objects
     * @return
     */
    public static Map<String, String> validateObject(Object first, Object... objects) {
        if (objects != null && objects.length > 0) {
            return validateList(Lists.asList(first,new Object[0]));
        }else {
            return validate(first,new Class[0]);
        }
    }

    public static void checkObject(Object t) throws OwnExcetpein{
        Map<String, String> vires = BeanValidator.validateObject(t);
        if (MapUtils.isNotEmpty(vires)) {
            throw new OwnExcetpein(vires.toString());
        }
    }
}
