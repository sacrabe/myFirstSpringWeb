package com.hjy.mapper;

import com.hjy.pojo.EmpExpr;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *
 * 员工工作经历Mapper
 *
 */

@Mapper
public interface EmpExprMapper {

//    @Select("select *from emp_expr")
//    public List<EmpExpr> findEmpExpr(Integer page , Integer pageSize);

    /**
     *
     * 批量保存员工工作经历
     *
     * @param exprList
     */

    void addEmpExprs(List<EmpExpr> exprList);

    void delete(List<Integer> ids);

    @Select("select *from emp_expr where emp_id = #{id}")
    List<EmpExpr> select(Integer id);




}
