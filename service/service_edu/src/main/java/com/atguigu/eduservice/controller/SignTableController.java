package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.SignList;
import com.atguigu.eduservice.entity.SignTable;
import com.atguigu.eduservice.service.AclUserService;
import com.atguigu.eduservice.service.SignListService;
import com.atguigu.eduservice.service.SignTableService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  签到表controller
 * </p>
 *
 * @author testjava
 * @since 2021-07-02
 */
@RestController
@RequestMapping("/eduservice/signtable")
public class SignTableController {
    @Autowired
    private SignListService signListService;
    @Autowired
    private SignTableService signTableService;
    @Autowired
    AclUserService aclUserService;

    @GetMapping("getTable/{id}")
    public R getTable(@PathVariable String id) {
        SignTable signTable = signTableService.getById(id);
        return R.ok().data("table",signTable);
    }

    @ApiOperation(value = "所有签到表列表")
    @GetMapping("findAll")
    public R findAllSignTable() {
        //调用service的方法实现查询所有的操作
        List<SignTable> list = signTableService.list(null);
        return R.ok().data("items",list);
    }

    // 逻辑删除的方法
    @ApiOperation(value = "逻辑删除签到表")
    @DeleteMapping("{id}")
    public R removeSignTable(@ApiParam(name = "id", value = "签到表ID", required = true)
                           @PathVariable String id) {
        boolean flag = signTableService.removeById(id);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
    //根据签到表id找签到学生
    @ApiOperation(value = "查看签到学生")
    @GetMapping("findAllSignList/{id}")
    public R findAllSignListByTeacherId(@PathVariable("id") String id) {
        //调用service的方法实现查询所有的操作
        QueryWrapper<SignList> wrapper = new QueryWrapper<>();
        wrapper.eq("table_id",id);
        List<SignList> list = signListService.list(wrapper);
        return R.ok().data("items",list);
    }

    //添加的方法
    @PostMapping("add")
    public R addSignTable(@RequestBody SignTable signTable) {
        boolean save = signTableService.save(signTable);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

}

