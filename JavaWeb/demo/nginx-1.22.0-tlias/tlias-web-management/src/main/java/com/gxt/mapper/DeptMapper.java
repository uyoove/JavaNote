package com.gxt.mapper;

import com.gxt.pojo.Dept;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
public interface DeptMapper {
    /**
     * 查询全部部门
     * @return
     */

    @Select("select * from dept")
    List<Dept> list();

    @Delete("delete from dept where id = #{id}")
    void delete(Integer id);

    @Options(keyProperty = "id",useGeneratedKeys = true)
    @Insert("insert into dept(name, create_time, update_time) values(#{name},#{createTime},#{updateTime})")
    void add(Dept dept);

    @Select("select * from dept where id = #{id}")
    Dept selectById(Integer id);

    @Update("update dept set name=#{name} where id = #{id}")
    void update(Dept dept);
}
