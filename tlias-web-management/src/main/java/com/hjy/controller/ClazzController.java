package com.hjy.controller;

import com.hjy.pojo.Clazz;
import com.hjy.pojo.ClazzQueryParam;
import com.hjy.pojo.PageResult;
import com.hjy.pojo.Result;
import com.hjy.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    @GetMapping("/clazzs")
    public Result findAll(ClazzQueryParam clazzQueryParam){
        log.info("查询所有班级信息");
        PageResult<Clazz> clazzList=  clazzService.findAll(clazzQueryParam);
        return  Result.success(clazzList);
    }

    @PostMapping("/clazzs")
    public Result addClazz(@RequestBody Clazz clazz){
        log.info("添加班级信息");
        clazzService.addClazz(clazz);
        return  Result.success();
    }
    @GetMapping("/clazzs/{id}")
    public Result selectById(@PathVariable Integer id){
        log.info("查询id为 {} 的班级信息",id);
        Clazz clazz = clazzService.selectById(id);
        return Result.success(clazz);
    }

    @PutMapping("/clazzs")
    public Result updateById(@RequestBody Clazz clazz){
        log.info("更新班级信息 {}",clazz);
        clazzService.updateClazz(clazz);
        return  Result.success();
    }
    @DeleteMapping("/clazzs/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("删除班级信息 {}",ids);
        clazzService.delete(ids);
        return  Result.success();
    }
}
