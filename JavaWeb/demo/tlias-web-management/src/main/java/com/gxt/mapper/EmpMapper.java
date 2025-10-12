package com.gxt.mapper;

import com.gxt.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    /**
     * 查询总记录数
     * @return
     */
//    @Select("select count(*) from emp")
//    public Long count();
//
//    /**
//     * 分页查询列表数据
//     * @param start
//     * @param pageSize
//     * @return
//     */
//    @Select("select * from emp limit #{start},#{pageSize}")
//    public List<Emp> page(Integer start,Integer pageSize);

    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    void delete(List<Integer> ids);

    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void save(Emp emp);

    @Select("select * from emp where id = #{id}")
    Emp selectById(Integer id);

    void update(Emp emp);

    /**
     *根据部门ID删除该部门下的员工数据
     * @param emp
     * @return
     */
    @Select("select * from emp where username=#{username} and password=#{password}")
    Emp getByUsernameAndPassWord(Emp emp);

    @Delete("delete from emp where dept_id = #{deptId}")
    void deleteByDeptId(Integer deptId);
}
