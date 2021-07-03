package com.atguigu.msmservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.atguigu.msmservice.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

@Service
public class MsmServiceImpl implements MsmService {

    @Autowired
    JavaMailSenderImpl mailSender;
    @Value("${spring.mail.username}")
    private String from;



    @Override
    public boolean sendByEmail(Map<String, Object> param, String email) {
        if(StringUtils.isEmpty(email)) return false;
        try{
            String emailServiceCode = param.get("code").toString();
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("注册验证码");
            message.setText("注册验证码是：" + emailServiceCode);
            message.setTo(email);
            message.setFrom(from);
            mailSender.send(message);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
}
