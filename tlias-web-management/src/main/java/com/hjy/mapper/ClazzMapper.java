package com.hjy.mapper;

import com.hjy.pojo.Clazz;
import com.hjy.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClazzMapper {


    List<Clazz> findAll(ClazzQueryParam clazzQueryParam);

    void addClazz(Clazz clazz);

    @Select("select * from clazz where id = #{id}")
    Clazz selectById(Integer id);

    void updateClazz(Clazz clazz);


    void delete(List<Integer> ids);
}
