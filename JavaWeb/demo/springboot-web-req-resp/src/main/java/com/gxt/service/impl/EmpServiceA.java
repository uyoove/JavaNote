package com.gxt.service.impl;

import com.gxt.dao.EmpDao;
import com.gxt.dao.impl.EmpDaoA;
import com.gxt.pojo.Emp;
import com.gxt.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class EmpServiceA implements EmpService {
   @Autowired //运行时,IOC容器会提供该类型的bean对象,并赋值给变量 - 依赖注入
    private EmpDao empdao;
    @Override
    public List<Emp> listEmp() {
        //2.数据进行转换处理
        List<Emp> empList = empdao.listEmp();
        empList.stream().forEach(emp -> {
            String gender = emp.getGender();
            if("1".equals(gender)){
                emp.setGender("男");
            }else if("2".equals(gender)){
                emp.setGender("女");
            }

            String job = emp.getJob();
            if("1".equals(job)){
                emp.setGender("讲师");
            }else if("2".equals(gender)){
                emp.setGender("班主任");
            }else{
                emp.setGender("就业指导");
            }
        });
        return empList;
    }
}
