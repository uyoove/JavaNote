package com.gxt.service;

import com.gxt.pojo.Emp;
import com.gxt.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {

    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    void delete(List<Integer> ids);

    void save(Emp emp);

    Emp selectById(Integer id);

    void update(Emp emp);

    Emp login(Emp emp);
}
