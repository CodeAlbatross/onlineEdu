package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.AclUser;
import com.atguigu.eduservice.mapper.AclUserMapper;
import com.atguigu.eduservice.service.AclUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-07-03
 */
@Service
public class AclUserServiceImpl extends ServiceImpl<AclUserMapper, AclUser> implements AclUserService {

}
