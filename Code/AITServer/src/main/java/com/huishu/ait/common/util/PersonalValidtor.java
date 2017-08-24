package com.huishu.ait.common.util;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.huishu.ait.entity.dto.RegisterDTO;  
  
/** 
 * Created by wb-zhangkenan on 2016/9/2. 
 */  
public class PersonalValidtor implements Validator{  
  
    /** 
     * 判断支持的JavaBean类型 
     * @param aClass 
     * @return 
     */  
    @Override  
    public boolean supports(Class<?> aClass) {  
        return RegisterDTO.class.equals(aClass);  
    }  
  
    /** 
     * 实现Validator中的validate接口 
     * @param obj 
     * @param errors 
     */  
    @Override  
    public void validate(Object obj, Errors errors) {  
        //把校验信息注册到Error的实现类里  
        ValidationUtils.rejectIfEmpty(errors,"userAccount",null,"姓名不能为空!");  
        ValidationUtils.rejectIfEmpty(errors,"telphone",null,"手机不能为空!");  
        ValidationUtils.rejectIfEmpty(errors,"userEmail",null,"邮箱不能为空!");  
        ValidationUtils.rejectIfEmpty(errors,"captcha",null,"验证码不能为空!");  
        ValidationUtils.rejectIfEmpty(errors,"park",null,"园区不能为空!");  
        ValidationUtils.rejectIfEmpty(errors,"userType",null,"用户类型不能为空!");  
    }  
} 