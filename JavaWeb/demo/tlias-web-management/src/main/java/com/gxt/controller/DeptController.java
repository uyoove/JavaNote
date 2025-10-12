package com.gxt.controller;

import com.gxt.anno.Log;
import com.gxt.pojo.Dept;
import com.gxt.pojo.Emp;
import com.gxt.pojo.Result;
import com.gxt.service.DeptService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j //日志
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 查询部门数据
     * @return
     */
    @GetMapping("/depts")
    public Result list(){
        List<Dept> deptList = deptService.list();
        log.info("查询全部部门数据");
        return Result.success(deptList);
    }

    /**
     * 根据id删除数据
     * @param id id值
     * @return
     */
    @Log
    @DeleteMapping("/depts/{id}")
    public Result delete(@PathVariable Integer id){
        deptService.delete(id);
        log.info("根据id删除部门:{}",id);
        return Result.success();
    }

    /**
     * 新增部门
     * @return
     */
    @Log
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept){
        deptService.add(dept);
        log.info("新增部门:{},id:{}",dept.getName(),dept.getId());
        return Result.success();
    }

    @GetMapping("/depts/{id}")
    public Result selectById(@PathVariable Integer id){
        Dept dept = deptService.selectById(id);
        log.info("查询部门姓名:{}",dept.getName());
        return Result.success(dept);
    }
    @Log
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept){
        deptService.update(dept);
        log.info("修改成功部门名:{}",dept.getName());
        return Result.success();
    }
}
