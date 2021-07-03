package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.SignList;
import com.atguigu.eduservice.entity.SignTable;
import com.atguigu.eduservice.service.AclUserService;
import com.atguigu.eduservice.service.SignListService;
import com.atguigu.eduservice.service.SignTableService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-07-02
 */
@RestController
@RequestMapping("/eduservice/signlist")
public class SignListController {
    @Autowired
    SignListService signListService;

    @Autowired
    SignTableService signTableService;

    @Autowired
    AclUserService aclUserService;

    //根据签到表ID签到
    @PostMapping("add/{tableId}/{studentId}")
    public R addSignTable(@PathVariable("tableId") String tableId,
                          @PathVariable("studentId") String studentId) {
        SignList signList = new SignList();
        signList.setTableId(tableId);
        signList.setStudentId(studentId);
        signList.setTeacherName(signTableService.getById(tableId).getTeacherName());
        signList.setStudentName(aclUserService.getById(studentId).getNickName());
        boolean save = signListService.save(signList);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }
    @ApiOperation(value = "所有未过期签到表列表")
    @GetMapping("findAll")
    public R findAllSignTable() {
        //调用service的方法实现查询所有的操作

        QueryWrapper<SignTable> wrapper = new QueryWrapper<>();
        wrapper.gt("expire_time",new Date().getTime());
        List<SignTable> list = signTableService.list(wrapper);
        return R.ok().data("items",list);
    }
}

