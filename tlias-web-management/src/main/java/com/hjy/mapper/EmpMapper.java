package com.hjy.mapper;

import com.hjy.pojo.Emp;
import com.hjy.pojo.EmpExpr;
import com.hjy.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * 员工表Mapper
 *
 *
 */

@Mapper
public interface EmpMapper {

    /**
     * 常规分页查询
     *
     */

    /*
    @Select("select count(*)from emp left join dept on emp.dept_id = dept.id")
    public long findEmpCount();

    @Select("select emp.*,dept.name deptName from emp " +
            "left join dept on emp.dept_id = dept.id " +
            "order by update_time desc " +
            "limit #{start},#{pageSize}")
    public List<Emp> findEmpByPage( Integer start, Integer pageSize);
    */


    /**
     *
     * Helper分页查询
     */

    //@Select("select emp.*,dept.name deptName from empleft join dept on emp.dept_id = dept.id order by update_time desc " )
    public List<Emp> list(EmpQueryParam empQueryParam);

    @Options(useGeneratedKeys = true,keyProperty = "id")   //获取插入emo时生成的主键id，并将主键id 赋给 Emp对象的id属性值  --主键返回
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            " values (#{username}, #{name}, #{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void addEmp(Emp emp);

    void delete(List<Integer> ids);

    @Select("select *from emp where id = #{id}")
    Emp select(Integer id);

    // 使用resultMap对多重结果进行手动封装
    Emp select2(Integer id);


    void updateById(Emp emp);

    // java自动封装
    @MapKey("pos")
    List<Map<String,Object>>  getEmpJobDate();

    @Select("select id, username, name from emp where username = #{username} and password = #{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}
