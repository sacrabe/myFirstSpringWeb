package com.hjy.controller;

import com.hjy.pojo.Dept;
import com.hjy.pojo.Result;
import com.hjy.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;
    // 指定get请求
    //@RequestMapping(value = "/dept",method = RequestMethod.GET)
    @GetMapping
    public Result findAll(){
        List<Dept> deptList = deptService.findAll();
        log.info("查询所有部门信息");
        return  Result.success(deptList);
    }


    // 方法一
    // 简单参数不推荐此方法，需要手动类型转换
//    @DeleteMapping("/depts")
//    public Result deleteById(String id){
//        deptService.deleteById(Integer.parseInt(id));
//        return Result.success();
//    }

    // 方法二 使用@RequestParam传入参数
    //  一旦声明了@RequestParam 该参数请求时必须传递，否则报错 （默认 require 为 true）

    // 要是不想必须传入此参数，可以设置 require 为 false
    //@RequestParam(value = "id",required = false)


//    @DeleteMapping("/depts")
//    public Result deleteById(@RequestParam("id") Integer id){
//        deptService.deleteById(id);
//        return Result.success();
//    }

    // 方法三 省略@RequestParam ，前提：参数名必须和请求参数名一致
    @DeleteMapping
    public Result deleteById(Integer id){
        deptService.deleteById(id);
        log.info("删除 部门 ：id ={}",id);
        return Result.success();
    }

    @PostMapping
    public Result updateById(@RequestBody Dept dept){
        deptService.add(dept);
        log.info("添加部门 {}",dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        Dept info = deptService.getInfo(id);
        log.info("查询部门 id={} 信息 ",id);
        return Result.success(info);
    }

    @PutMapping
    public Result updateName(@RequestBody Dept dept){
        deptService.updateName(dept);
        log.info("更新部门 {}信息",dept);
        return Result.success();
    }

}
