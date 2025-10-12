package com.gxt.controller;

import com.gxt.pojo.Emp;
import com.gxt.pojo.Result;
import com.gxt.service.EmpService;
import com.gxt.service.impl.EmpServiceA;
import com.gxt.utills.XmlParserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmpController {
    @Autowired //运行时,IOC容器会提供该类型的bean对象,并赋值给变量 - 依赖注入
    private EmpService empService;
    @RequestMapping("/listEmp")
    public Result list(){
        List<Emp> empList = empService.listEmp();

        //3.响应数据
        return Result.success(empList);
    }
}
