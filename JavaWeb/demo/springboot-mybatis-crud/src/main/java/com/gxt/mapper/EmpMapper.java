package com.gxt.mapper;

import com.gxt.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    //根据ID删除数据
    @Delete("delete from emp where id = #{id}")
    public void delete(Integer id);

    //新增员工
    @Options(keyProperty = "id",useGeneratedKeys = true) //获取的是id,获取返回的值
    @Insert("insert into emp(username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "values (#{username},#{password},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    public void insert(Emp emp);

//    @Update("update emp set username=#{username},name=#{name},gender=#{gender},image=#{image},job=#{job},entrydate=#{entrydate}," +
//            "dept_id=#{deptId},update_time=#{updateTime} where id = #{id}")
//    public void update(Emp emp);

    @Select("select * from emp where id=#{id}")
    public Emp getById(int id);

//    @Select("select * from emp where name like concat('%',#{name},'%') and gender=#{gender} and entrydate between #{begin} and #{end} order by update_time desc")
//    public List<Emp> list(String name, Short gender, LocalDate begin,LocalDate end);

    public List<Emp> list(String name, Short gender, LocalDate begin,LocalDate end);

    public void update(Emp emp);

    public void deleteByIds(List<Integer> ids);
}
