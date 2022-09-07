package com.wang.dao;

import com.wang.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DeptDao {

    public boolean addDept(Dept dept);
    public Dept queryById(Long id);
    public List<Dept> queryAll();

}
