package com.hjy.service;

import com.hjy.pojo.Clazz;
import com.hjy.pojo.ClazzQueryParam;
import com.hjy.pojo.PageResult;

import java.util.List;


public interface ClazzService {
    public PageResult<Clazz> findAll(ClazzQueryParam clazzQueryParam);

    public void addClazz(Clazz clazz);

    Clazz selectById(Integer id);

    void updateClazz(Clazz clazz);

    void delete(List<Integer> ids);
}
