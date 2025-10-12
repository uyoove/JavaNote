package com.gxt.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gxt.mapper.EmpMapper;
import com.gxt.pojo.Emp;
import com.gxt.pojo.PageBean;
import com.gxt.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

//    @Override
//    public PageBean page(Integer page, Integer pageSize) {
//        PageBean pageBean = new PageBean();
//        Integer start = (page - 1) * pageSize;
//        pageBean.setRows(empMapper.page(start,pageSize));
//        pageBean.setTotal(empMapper.count());
//        return pageBean;
//    }
    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        //1.设置分页参数
        PageHelper.startPage(page,pageSize);
        List<Emp> list = empMapper.list(name,gender,begin,end);
        Page<Emp> p = (Page<Emp>) list;
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.save(emp);
    }

    @Override
    public Emp selectById(Integer id) {

        return empMapper.selectById(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernameAndPassWord(emp);
    }
}
