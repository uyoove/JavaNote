package com.gxt;

import com.gxt.mapper.EmpMapper;
import com.gxt.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SpringbootMybatisCrudApplicationTests {
    @Autowired
    private EmpMapper empMapper;

    //@Test
    public void testDelete(){
        empMapper.delete(16);
    }
    //@Test
    public void testInsert(){
        Emp emp = new Emp();
        emp.setUsername("gxt2");
        emp.setPassword("123456");
        emp.setDeptId(1);
        emp.setName("龚孝天2");
        emp.setImage("1.jpg");
        emp.setGender((short) 1);
        emp.setJob((short) 1);
        emp.setEntrydate(LocalDate.of(2000,1,2));
        emp.setUpdateTime(LocalDateTime.now());
        emp.setCreateTime(LocalDateTime.now());
        empMapper.insert(emp);
        System.out.println(emp.getId());
    }
    //@Test
    public void testUpdate(){
        Emp emp = new Emp();
        emp.setId(20);
        emp.setUsername("Tom121");
        emp.setName("汤姆111");
        emp.setGender((short) 2);
        //emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }
    //@Test
    public void testGetById(){
        Emp emp = empMapper.getById(1);
        System.out.println(emp);
    }
    //@Test
    public void testList(){
        List<Emp> list = empMapper.list(null, (short)1,null, LocalDate.of(2020, 1, 1));
        System.out.println(list);
    }
    @Test
    public void testDeleteByIds(){
        List<Integer> list = Arrays.asList(18,19,20);
        empMapper.deleteByIds(list);
    }
}
