package com.learn.common.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author coffee
 * @since 2021-06-03 16:54
 */
public class ListValueConstraintValidator implements ConstraintValidator<ListValue,Integer> {

    //初始化方法
    @Override
    public void initialize(ListValue constraintAnnotation) {
        int[] values = constraintAnnotation.values();

    }

    //判断是否校验成功
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return false;
    }
}