package com.hjy.controller;


import com.hjy.anno.LogOperation;
import com.hjy.pojo.Emp;
import com.hjy.pojo.EmpQueryParam;
import com.hjy.pojo.PageResult;
import com.hjy.pojo.Result;
import com.hjy.service.EmpService;
import com.hjy.service.impl.EmpServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工管理controller
 *
 */

@Slf4j
@RestController
public class EmpController {
    @Autowired
    private EmpServiceImpl empService;
    @GetMapping("/emps")
    public Result findEmp(EmpQueryParam empQueryParam){
        log.info("分页查询，{}",empQueryParam);

        PageResult<Emp> empByPage = empService.findEmpByPage(empQueryParam);


        return Result.success(empByPage);
    }
    @LogOperation
    @PostMapping("/emps")
    public Result addEmp(@RequestBody Emp emp){
        log.info("新增员工 {}",emp);
        empService.addEmp(emp);
        return  Result.success();
    }

    @LogOperation
    @DeleteMapping("/emps")
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除员工：{}",ids);
        empService.delete(ids);

        return Result.success();



    }

    @GetMapping("/emps/{id}")
    public Result select(@PathVariable Integer id ){
        log.info("查询id为 {} 的员工信息",id);
        Emp emp = empService.select(id);
        return Result.success(emp);
    }
    @LogOperation
    @PutMapping("/emps")
    public  Result update(@RequestBody Emp emp){
        log.info("更新id为---- {}---- 员工数据",emp.getId());
        empService.update(emp);
        return  Result.success();
    }
}
