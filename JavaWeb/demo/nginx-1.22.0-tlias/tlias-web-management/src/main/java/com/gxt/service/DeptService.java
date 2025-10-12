package com.gxt.service;

import com.gxt.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 查询全部部门
     * @return
     */
    List<Dept> list();

    /**
     * 删除所有部门
     */
    void delete(Integer id);

    void add(Dept dept);

    Dept selectById(Integer id);

    void update(Dept dept);
}
