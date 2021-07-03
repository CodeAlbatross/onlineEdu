package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.SignList;
import com.atguigu.eduservice.entity.SignTable;
import com.atguigu.eduservice.service.AclUserService;
import com.atguigu.eduservice.service.SignListService;
import com.atguigu.eduservice.service.SignTableService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.time.DateFormatUtils;
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
    @PostMapping("add/{tableId}/{studentId}/{studentName}")
    public R addSignTable(@PathVariable("tableId") String tableId,
                          @PathVariable("studentId") String studentId,
                          @PathVariable("studentName") String studentName) {
        SignList signList = new SignList();
        signList.setTableId(tableId);
        signList.setStudentId(studentId);
        signList.setTeacherName(signTableService.getById(tableId).getTeacherName());
        signList.setStudentName(studentName);
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
        QueryWrapper<SignTable> wrapper = new QueryWrapper<>();
        //解决格式不匹配的问题
        String end = DateFormatUtils.format(new Date().getTime(),"yyyy-MM-dd HH:mm:ss");
        wrapper.gt("expire_time",end);
        List<SignTable> list = signTableService.list(wrapper);
        System.out.println(list);
        return R.ok().data("items",list);
    }
}

