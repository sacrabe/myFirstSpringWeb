package com.hjy.service;

import com.hjy.mapper.DeptMapper;
import com.hjy.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DeptService {

    public List<Dept> findAll();
    public void deleteById(Integer id);

    void add(Dept dept);

    Dept getInfo(Integer id);



    void updateName(Dept dept);
}
