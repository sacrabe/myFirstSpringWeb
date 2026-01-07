package com.hjy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hjy.mapper.ClazzMapper;
import com.hjy.pojo.Clazz;
import com.hjy.pojo.ClazzQueryParam;
import com.hjy.pojo.PageResult;
import com.hjy.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired

    private ClazzMapper clazzMapper;
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public PageResult<Clazz> findAll(ClazzQueryParam clazzQueryParam) {
        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());
        List<Clazz>  clazzList = clazzMapper.findAll(clazzQueryParam);
        Page<Clazz> p = (Page<Clazz>) clazzList;
        p.forEach(clazz -> {
            if (clazz.getEndDate().isBefore(LocalDate.now())) {
                clazz.setStatus("已结课");
            } else if (clazz.getBeginDate().isAfter(LocalDate.now())) {
                clazz.setStatus("未开课");
            } else {
                clazz.setStatus("开课中");
            }
        });
        return new PageResult<>(p.getTotal(),p.getResult());
    }

    @Override
    public void addClazz(Clazz clazz) {
        clazz.setCreateTime(LocalDate.now());
        clazz.setUpdateTime(LocalDate.now());
        clazzMapper.addClazz(clazz);
    }

    @Override
    public Clazz selectById(Integer id) {
        Clazz clazz = clazzMapper.selectById(id);
        return clazz;
    }

    @Override
    public void updateClazz(Clazz clazz) {
        clazz.setUpdateTime(LocalDate.now());
        clazzMapper.updateClazz(clazz);

    }

    @Override
    public void delete(List<Integer> ids) {
        clazzMapper.delete(ids);
    }
}
