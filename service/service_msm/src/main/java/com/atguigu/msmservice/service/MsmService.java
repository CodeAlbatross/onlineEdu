package com.atguigu.msmservice.service;

import java.util.Map;

public interface MsmService {


    boolean sendByEmail(Map<String, Object> param, String email);
}
