package com.hjy.service;

import com.hjy.pojo.Emp;
import com.hjy.pojo.EmpQueryParam;
import com.hjy.pojo.LoginInfo;
import com.hjy.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {

//    public long findEmpCount();

    //public PageResult<Emp> findEmpByPage(Integer page , Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);
    public  PageResult<Emp> findEmpByPage(EmpQueryParam empQueryParam);

    public  void addEmp(Emp emp);

    void delete(List<Integer> ids);

    public Emp select(Integer id);

    void update(Emp emp);


    LoginInfo login(Emp emp);
}
