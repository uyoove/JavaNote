package com.gxt.dao.impl;

import com.gxt.dao.EmpDao;
import com.gxt.pojo.Emp;
import com.gxt.utills.XmlParserUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component //将当前类交给IOC容器管理,成为IOC容器的bean
public class EmpDaoA implements EmpDao {
    @Override
    public List<Emp> listEmp(){
        //1.加载解析XMl文件
        String file = this.getClass().getClassLoader().getResource("emp.xml").getFile();
        System.out.println(file);
        List<Emp> empList = XmlParserUtils.parse(file, Emp.class);
        return empList;
    }
}
