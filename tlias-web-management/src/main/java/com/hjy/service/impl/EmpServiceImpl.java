package com.hjy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hjy.mapper.EmpExprMapper;
import com.hjy.mapper.EmpMapper;
import com.hjy.pojo.*;
import com.hjy.service.EmpLogService;
import com.hjy.service.EmpService;
import com.hjy.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpLogServiceImpl empLogService;

    /*@Override
    public long findEmpCount() {
        return empMapper.findEmpCount();
    }*/

    /*@Override
    public PageResult<Emp> findEmpByPage(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin,LocalDate end) {
        PageHelper.startPage(page,pageSize);
        List<Emp> empList = empMapper.list(name, gender, begin, end);
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }*/

    @Override
    public PageResult<Emp> findEmpByPage(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize() );
        List<Emp> empList = empMapper.list(empQueryParam);
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void addEmp(Emp emp) {
        //1. 保存基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.addEmp(emp);  // 执行此方法时 @Option会进行 主键回调



        List<EmpExpr> exprList = emp.getExprList();  // 若为空，则 调用方法会报错
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(expr->{
                expr.setEmpId(emp.getId());
            });
            empExprMapper.addEmpExprs(exprList);
        }


        return ;
    }

    // 涉及操作两次数据库  应该开启事务
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {

        empExprMapper.delete(ids);
        empMapper.delete(ids);

    }
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Emp select(Integer id) {
        Emp emp = empMapper.select(id);
        List<EmpExpr> empExprs = empExprMapper.select(id);
        emp.setExprList(empExprs);
        return emp;
    }
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.updateById(emp);
        empExprMapper.delete(Arrays.asList(emp.getId()));

        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));

        }

        empExprMapper.addEmpExprs(exprList);

    }




    /*
    @Override
    public PageResult<Emp> findEmpByPage(Integer page, Integer pageSize) {
        Integer start = (page-1)*pageSize;
        List<Emp> empByPage = empMapper.findEmpByPage(start, pageSize);
        long total = empMapper.findEmpCount();
        return new PageResult<Emp>(total,empByPage);
    }
    */
    @Override
    public LoginInfo login(Emp emp) {
        //1. 调用mapper接口, 根据用户名和密码查询员工信息
        Emp e = empMapper.selectByUsernameAndPassword(emp);

        //2. 判断: 判断是否存在这个员工, 如果存在, 组装登录成功信息
        if(e != null){
            log.info("登录成功, 员工信息: {}", e);
            //生成JWT令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("username", e.getUsername());
            String jwt = JwtUtils.generateToken(claims);

            return new LoginInfo(e.getId(), e.getUsername(), e.getName(), jwt);
        }

        //3. 不存在, 返回null
        return null;
    }

}
