#  JavaWeb

[TOC]

## 1.web入门

### 1.1 springbootweb快速入门

![image-20250916135119865](img/image-20250916135119865.png)

**步骤:**

1. **创建spingboot工程,勾选web相关依赖**

   ![image-20250916135326645](img/image-20250916135326645.png)

   ![image-20250916135445490](img/image-20250916135445490.png)

2. **定义HelloController类增加方法hello,并且添加注解**

   ```java
   //请求处理类 加上注解才能标识为请求处理类
   @RestController
   public class HelloController {
       //这个注解代表浏览器处理/hello地址就会调用下面的方法
       @RequestMapping("/hello")
       public String hello(){
           System.out.println("Hello");
           return "Hello";
       }
   }
   ```

3. **运行测试**

​	   **运行启动类,自动占用8080端口**

​		**localhost:8080/hello** 

### 1.2 HTTP协议

#### 1.2.1 概述

概念: 超文本传输协议,规定了浏览器与服务器之前数据传输的规则

![image-20250916141620622](img/image-20250916141620622.png)

![image-20250916141833112](img/image-20250916141833112.png)

**无状态无记忆是重点**

#### 1.2.2 请求协议

**格式:**

![image-20250916142630465](img/image-20250916142630465.png)

![image-20250916142706353](img/image-20250916142706353.png)

![image-20250916142333573](img/image-20250916142333573.png)

#### 1.2.3 响应协议

**格式:**

![image-20250916143128818](img/image-20250916143128818.png)

![image-20250916143212838](img/image-20250916143212838.png)

![image-20250916143544520](img/image-20250916143544520.png)

#### 1.2.4 协议解析

web服务器来解析Http协议,避免程序员繁琐解析操作.

著名的web服务器有**TomCat**

### 1.3 TomCat

![image-20250916171155746](img/image-20250916171155746.png)

#### 1.3.1 介绍

![image-20250916171553379](img/image-20250916171553379.png)

####  1.3.2 基本使用

#### ![image-20250916172058839](img/image-20250916172058839.png)

## 2.请求响应

### 2.1 概述

![image-20250916174217059](img/image-20250916174217059.png)

![image-20250916174335151](img/image-20250916174335151.png)

​	**两个对象:**

- 请求(HttpServletRequest): 获取请求数据
- 响应(HttpServletResponse): 设置响应数据

​	**BS架构:Browser/Server,浏览器/服务器架构模式.客户端只需要浏览器,应用程序的逻辑和数据都存储在服务器**

​	**CS架构: Clinet/Server,客户端/服务器架构模式(相当于要单独安装一个客户端软件)**

### 2.2 请求

#### 2.2.1 postman

![image-20250916223928593](img/image-20250916223928593.png)

**Postman是一款网页调试与发送网页HTTP请求的Chrome插件**

**作用: 常用于进行接口测试**

![image-20250916225818291](img/image-20250916225818291.png)

#### 2.2.2 简单参数

在原始的web程序中,获取请求参数,需要通过HttpServletRequest对象手动获取

```java
@RestController
public class RequestController {
    //原始方式
    @RequestMapping("/simpleParam")
    public String simpleParam(HttpServletRequest request){
        //获取请求参数
        String name = request.getParameter("name");
        String ageStr = request.getParameter("age");
        int age = Integer.parseInt(ageStr);
        System.out.println(name + ":" + age);
        return "OK";
    }
}
```

**上面方法太过繁琐**

------

**下面是简单方法**

```java
@RestController
public class RequestController2 {
    //简单方法
    @RequestMapping("/simpleParam2")
    public String simpleParam(String name,Integer age){
        System.out.println(name + ":" + age);
        return "OK";
    }
}
```

![image-20250916233152453](img/image-20250916233152453.png)

有一个required形参可选,默认是TRUE,代表如果请求不存在会报错,如果是FALSE就不会

**总结:**

![image-20250916233508314](img/image-20250916233508314.png)

#### 2.2.3 实体参数

**简单实体对象: 请求参数名与对象的属性名相同,定义POJO接受即可**

**方法:**

```java
@RestController
public class RequestController3 {
    @RequestMapping("/simplePojo")
    public String simplePojo(User user){
        System.out.println(user);
        return "OK";
    }
}
```

用户类:

```java
public class User {
    private String name;
    private Integer age;
    //get set toString方法省略
}

```

![image-20250916234425705](img/image-20250916234425705.png)

#### 2.2.4 数组集合参数

**数组参数**

![image-20250917123250364](img/image-20250917123250364.png)

**集合参数**

要加注解@RequestParam

![image-20250917123523485](img/image-20250917123523485.png)

#### 2.2.5 日期参数

**需要使用@DateTimeFormat注解完成日期参数格式转换**

例如请求:

![image-20250917124259479](img/image-20250917124259479.png)

```java
@RestController
public class RequestController4 {
    @RequestMapping("/dataParam")
    public String dataParam(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime updateTime){
        System.out.println(updateTime);
        return "OK";
    }
}
```

#### 2.2.6 Json参数

**只能post请求**

![image-20250917125414187](img/image-20250917125414187.png)

定义类和请求的属性相同,然后用@RequestBody注解将Json封装成实体对象(如果不使用注解返回的是null)

```java
@RestController
public class RequestController5 {
    @RequestMapping("/jsonParam")
    public String jsonParam(@RequestBody User user){
        System.out.println(user);
        return "ok";
    }
}
```

#### 2.2.7 路径参数

![image-20250917130456845](img/image-20250917130456845.png)

```java
@RestController
public class RequestController6 {
    @RequestMapping("/path/{id}")
    public String pathParam(@PathVariable Integer id){
        System.out.println(id);
        return "ok";
    }
}
```
**多个路径使用:**

![image-20250917130859767](img/image-20250917130859767.png)

### 2.3 响应

#### 2.3.1 @ResponseBody

![image-20250917153852707](img/image-20250917153852707.png)

#### 2.3.2 统一响应结果

![image-20250917154620033](img/image-20250917154620033.png)

### 2.4 分层解耦

#### 2.4.1 三层架构

![image-20250917163058600](img/image-20250917163058600.png)

​		![image-20250917163148780](img/image-20250917163148780.png)

![image-20250917164433935](img/image-20250917164433935.png)

#### 2.4.2 分层解耦

![image-20250917172932991](img/image-20250917172932991.png)

![image-20250917173258470](img/image-20250917173258470.png)

#### 2.4.3 入门

**交给IOC容器管理: @Component**

**从IOC中取出,并赋值给该变量-依赖注入: @Autowired**

![image-20250917182333770](img/image-20250917182333770.png)

1. **将实现类交给IOC容器管理**

  ```java
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
  ```

   2.**将IOC中对象取出,并且赋值**

```java
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
```

**如果有两个类,要切换只需要把 @Component写到需要的那个类即可**

#### 2.4.4 IOC

容器管理

![image-20250917183450153](img/image-20250917183450153.png)

![image-20250917184014773](img/image-20250917184014773.png)

![image-20250917184443099](img/image-20250917184443099.png)

#### 2.4.5 DI

依赖注入

![image-20250917224226222](img/image-20250917224226222.png)

**可以通过以下方法解决**

**1.@Primary 设置优先级,例如:**

![image-20250917224320796](img/image-20250917224320796.png)

**2.@Qualifer("指定的bean名"),例如:**

![image-20250917224523863](img/image-20250917224523863.png)

**3.@Resource(name = "指定的bean名"),例如:**

![image-20250917224730076](img/image-20250917224730076.png)

**不用加@Autowired !**

![image-20250917224910289](img/image-20250917224910289.png)

![image-20250917225022455](img/image-20250917225022455.png) 

## 3.Mybatis

其他请见Mybatis笔记

### 3.1 lombok

需要Maven引入

![image-20250927114924292](img/image-20250927114924292.png)

### 3.2 数据库连接池

![image-20250926221516245](img/image-20250926221516245.png)

![image-20250926222701724](img/image-20250926222701724.png)

### 3.3 入门

![image-20250927110740600](img/image-20250927110740600.png)

![image-20250927115706670](img/image-20250927115706670.png)

如果查询乱码可以这样设置:

![image-20250927124628209](img/image-20250927124628209.png)

![image-20250927132650744](img/image-20250927132650744.png)

```properties
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/sqldemo2
spring.datasource.username=root
spring.datasource.password=123456
```

日志输出

```properties
mybatis.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl
```

**记关键字 mybatislog就行**

### 3.4 操作

#### 3.4.1 删除

- mapper接口

```java
@Mapper
public interface EmpMapper {

    //根据ID删除数据
    @Delete("delete from emp where id = #{id}")
    public void delete(Integer id);
}

```

- 测试代码

```java
@SpringBootTest
class SpringbootMybatisCrudApplicationTests {
    @Autowired
    private EmpMapper empMapper;

    @Test
    public void testDelete(){
        empMapper.delete(17);
    }
}
```

**预编译SQL:**

![image-20250928121005462](img/image-20250928121005462.png)

![image-20250928121942971](img/image-20250928121942971.png)

![image-20250928122001120](img/image-20250928122001120.png)

#### 3.4.2 新增

- mapper接口

  ```java
   //新增员工
      @Insert("insert into emp(username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
              "values (#{username},#{password},#{name},#{gender},#{image},#{job},#{entrydate},#{dept_id},#{createTime},#{updateTime})")
      public void insert(Emp emp);
  ```
- 测试代码
	
	```java
	@Test
	    public void testInsert(){
	        Emp emp = new Emp();
	        emp.setUsername("gxt");
	        emp.setPassword("123456");
	        emp.setDeptId(1);
	        emp.setName("龚孝天");
	        emp.setImage("1.jpg");
	        emp.setGender((short) 1);
	        emp.setJob((short) 1);
	        emp.setEntrydate(LocalDate.of(2000,1,2));
	        emp.setUpdateTime(LocalDateTime.now());
	        emp.setCreateTime(LocalDateTime.now());
	        empMapper.insert(emp);
	    }
	```

**主键返回:**

![image-20250928144104781](img/image-20250928144104781.png)

在对应的mapper接口上加上这个注释

```java
@Options(keyProperty = "id",useGeneratedKeys = true) //获取的是id,获取返回的值
```

#### 3.4.3 更新

- mapper接口

  ```java
  @Update("update emp set username=#{username},name=#{name},gender=#{gender},image=#{image},job=#{job},entrydate=#{entrydate}," +
              "dept_id=#{deptId},update_time=#{updateTime} where id = #{id}")
      public void update(Emp emp);
  ```

- 测试代码

  ```java
  @Test
      public void testUpdate(){
          Emp emp = new Emp();
          emp.setId(20);
          emp.setUsername("gxt4");
          emp.setName("龚孝天3");
          emp.setImage("1.jpg");
          emp.setGender((short) 1);
          emp.setJob((short) 2);
          emp.setEntrydate(LocalDate.of(2000,1,2));
          emp.setUpdateTime(LocalDateTime.now());
          emp.setCreateTime(LocalDateTime.now());
          empMapper.update(emp);
      }
  ```

#### 3.4.4 查询

**根据ID查询:**

- mapper接口

```java
@Results({
            @Result(column = "dept_id",property = "deptId"),
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "update_time",property = "updateTime")
    })
    @Select("select * from emp where id=#{id}")
    public Emp getById(int id);
```

或者设置:

```java
mybatis.configuration.map-underscore-to-camel-case=true
```

自动映射了驼峰命名a_fuck -> aFuck

- 测试代码

 ```java
  @Test
     public void testGetById(){
         Emp emp = empMapper.getById(1);
         System.out.println(emp);
     }
 ```

  ![image-20250928204640043](img/image-20250928204640043.png)

![image-20250928205443674](img/image-20250928205443674.png)

**列表条件查询:**

- mapper接口

  ```java
  @Select("select * from emp where name like '%${name}%' and gender=#{gender} and entrydate between #{begin} and #{end} order by update_time desc") //在''中不能使用#{}要用${}
      public List<Emp> list(String name, Short gender, LocalDate begin,LocalDate end);
  ```

- 测试代码

  ```java
   @Test
      public void testList(){
          List<Emp> list = empMapper.list("张", (short) 1, LocalDate.of(2010, 1, 1), LocalDate.of(2020, 1, 1));
          System.out.println(list);
      }
  ```

![image-20250928211108770](img/image-20250928211108770.png)

**解决${}引起的sql办法**

```java
@Select("select * from emp where name like concat('%',#{name},'%') and gender=#{gender} and entrydate between #{begin} and #{end} order by update_time desc")
    public List<Emp> list(String name, Short gender, LocalDate begin,LocalDate end);
```

**用concat()进行字符串拼接**

![image-20250928212011384](img/image-20250928212011384.png)

### 3.5 XML映射文件

![image-20250929083417430](img/image-20250929083417430.png)

- 先在官网找约束文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
```

- 完整mapper.xml

```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE mapper
          PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
          "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <mapper namespace="com.gxt.mapper.EmpMapper">
      <select id="list" resultType="com.gxt.pojo.Emp">
          select * from emp where name like concat('%',#{name},'%') and gender=#{gender} and entrydate between #{begin} and #{end} order by update_time desc
      </select>
  </mapper>
```

- mapper接口

```java
public List<Emp> list(String name, Short gender, LocalDate begin,LocalDate end);
```

****

**IDEA插件:MybatisX**

![image-20250929085618937](img/image-20250929085618937.png)

------

### 3.6 动态SQL

随着用户的输入和外部条件的变化而变化的SQL语句,称为动态SQL

#### 3.6.1 IF

对上述情况进行约束，这样输入就可以选择为空

- xml文件改写

```xml
<select id="list" resultType="com.gxt.pojo.Emp">
        select * from emp
        <where>
            <if test="name!=null">
                name like concat('%',#{name},'%')
            </if>
            <if test="gender!=null">
                and gender=#{gender}
            </if>
            <if test="begin!=null and end!=null">
                and entrydate between #{begin} and #{end}
            </if>
        </where>
        order by update_time desc
    </select>
```

- 测试代码

```java
@Test
    public void testList(){
        List<Emp> list = empMapper.list("张", null,null, LocalDate.of(2020, 1, 1));
        System.out.println(list);
    }
```

![image-20250929091702267](img/image-20250929091702267.png)

**UPDATE的<set>标签和<where>标签类似,他会将多余,去掉**

#### 3.6.2 foreach

比如说批量删除:

- mapper文件

  ```java
  public void deleteByIds(List<Integer> ids);
  ```

- xml文件

  ```xml
  <delete id="deleteByIds">
          delete from emp where id in
          <foreach collection="ids" item="id" separator="," open="(" close=")">
              #{id}
          </foreach>
      </delete>
  ```

- 测试代码

  ```java
  @Test
      public void testDeleteByIds(){
          List<Integer> list = Arrays.asList(18,19,20);
          empMapper.deleteByIds(list);
      }
  ```

  ![image-20250929104506986](img/image-20250929104506986.png)

#### 3.6.3 sql&&include

用来优化代码,实现代码复用

![image-20250929104743607](img/image-20250929104743607.png)

## 4.案例

### 4.1 准备过程

**需求说明**

![image-20250926223733234](img/image-20250926223733234.png)

![image-20250929144735438](img/image-20250929144735438.png)

![image-20250929154351551](img/image-20250929154351551.png)

![image-20250929154541233](img/image-20250929154541233.png)

![image-20250929154811915](img/image-20250929154811915.png)

### 4.2 查询部门

#### 4.2.1 初始准备

![image-20250929160108794](img/image-20250929160108794.png)

#### 4.2.2 Controller层接收请求

```java
@Slf4j //日志
@RestController
public class DeptController {

    @RequestMapping("/depts")
    public Result list(){
        log.info("查询全部部门数据");
        return Result.success();
    }
}
```

如果遇到日志出现找不到log的错误,需要检查IDEA的配置

<img src="img/image-20250929162229576.png" alt="image-20250929162229576" style="zoom:150%;" />

​		正确输出:

![image-20250929162457395](img/image-20250929162457395.png)

![image-20250929162516424](img/image-20250929162516424.png)

​	**注意上述方法可以通过,post,get,put....多种方式响应成功,而不能实现只有get可以实现**

​	**需要在Controller这样改:**

```java
@Slf4j //日志
@RestController
public class DeptController {
    @GetMapping("/depts")
    public Result list(){
        log.info("查询全部部门数据");
        return Result.success();
    }
}
```

#### 4.2.3 Controller层响应请求

- DeptServiceImpl方法

  ```java
  @Service
  public class DeptServiceImpl implements DeptService {
  
      @Autowired
      private DeptMapper deptMapper;
  
      @Override
      public List<Dept> list() {
          return deptMapper.list();
      }
  }
  ```

- DeptMapper接口

  ```java
  @Mapper
  public interface DeptMapper {
      /**
       * 查询全部部门
       * @return
       */
      @Select("select * from dept")
      List<Dept> list();
  }
  
  ```

- DeptController

  ```java
  @Slf4j //日志
  @RestController
  public class DeptController {
  
      @Autowired
      private DeptService deptService;
  
      @GetMapping("/depts")
      public Result list(){
          List<Dept> deptList = deptService.list();
          log.info("查询全部部门数据");
          return Result.success(deptList);
      }
  }
  ```

- 结果![image-20250929164547709](img/image-20250929164547709.png)

#### 4.2.4 前后端联调

![image-20251002182208791](img/image-20251002182208791.png)

他会交给TomCat服务器解析成8080端口

![image-20251002182139903](img/image-20251002182139903.png)

### 4.3 删除部门

接口信息:

![image-20251002182531476](img/image-20251002182531476.png)

响应数据:

![image-20251002182731678](img/image-20251002182731678.png)

**mapper接口**

```JAVA
@DeleteMapping("/depts/{id}")
    public Result delete(@PathVariable Integer id){
        deptService.delete(id);
        log.info("根据id删除部门:{}",id);
        return Result.success();
    }
```

**controller层**

```JAVA
@DeleteMapping("/depts/{id}")
    public Result delete(@PathVariable Integer id){
        deptService.delete(id);
        log.info("根据id删除部门:{}",id);
        return Result.success();
    }
```

其他略

### 4.4 新增部门

**接口信息**

![image-20251002184359305](img/image-20251002184359305.png)

**请求参数**

![image-20251002184436165](img/image-20251002184436165.png)

**响应参数**

![image-20251002184601963](img/image-20251002184601963.png)

![image-20251002185033891](img/image-20251002185033891.png)

Mapper接口:

```JAVA
    @Options(keyProperty = "id",useGeneratedKeys = true)
    @Insert("insert into dept(name, create_time, update_time) values(#{name},#{createTime},#{updateTime})")
    void add(Dept dept);
```

Service层:

```java
@Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.add(dept);
    }
```

Controller层:

```java
/**
     * 新增部门
     * @return
     */
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept){
        deptService.add(dept);
        log.info("新增部门:{},id:{}",dept.getName(),dept.getId());
        return Result.success();
    }
```

![image-20251002191255543](img/image-20251002191255543.png)

也可以直接抽取到类

### 4.5 修改部门

#### 4.5.1 根据id查询(回显数据)

![image-20251002201710706](img/image-20251002201710706.png)

![image-20251002202023520](img/image-20251002202023520.png)

**mapper接口**

```java
@Select("select * from dept where id = #{id}")
    Dept selectById(Integer id);
```

**Controller层**

```java
@GetMapping("/depts/{id}")
    public Result selectById(@PathVariable Integer id){
        Dept dept = deptService.selectById(id);
        log.info("查询部门姓名:{}",dept.getName());
        return Result.success(dept);
    }
```

其他略

#### 4.5.2 修改

![image-20251002203239088](img/image-20251002203239088.png)

响应数据

![image-20251002203300913](img/image-20251002203300913.png)

**mappe接口**

```java
@Update("update dept set name=#{name} where id = #{id}")
    void update(Dept dept);
```

**Controller层**

```java
@PutMapping("/depts")
    public Result update(@RequestBody Dept dept){
        deptService.update(dept);
        log.info("修改成功部门名:{}",dept.getName());
        return Result.success();
    }
```

**Service层**

```java
@Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
```

### 4.6 员工管理

#### 4.6.1 分页查询

### ![image-20251002215456374](img/image-20251002215456374.png)

![image-20251002215628464](img/image-20251002215628464.png)

![image-20251002215854530](img/image-20251002215854530.png)

Mapper接口:

```JAVA
/**
     * 查询总记录数
     * @return
     */
    @Select("select count(*) from emp")
    public Long count();

    /**
     * 分页查询列表数据
     * @param start
     * @param pageSize
     * @return
     */
    @Select("select * from emp limit #{start},#{pageSize}")
    public List<Emp> page(Integer start,Integer pageSize);
```

Service层:

```java
@Override
    public PageBean page(Integer page, Integer pageSize) {
        PageBean pageBean = new PageBean();
        Integer start = (page - 1) * pageSize;
        pageBean.setRows(empMapper.page(start,pageSize));
        pageBean.setTotal(empMapper.count());
        return pageBean;
    }
```

Controller层:

```java
@GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer pageSize){
        log.info("分页查询,参数:{},{}",page,pageSize);
        PageBean pageBean = empService.page(page,pageSize);
        return Result.success(pageBean);
    }
```

#### 4.6.2 分页插件PageHelper

![image-20251003152436715](img/image-20251003152436715.png)

**插件依赖:**

![image-20251003152513556](img/image-20251003152513556.png)

#### 4.6.3 条件分页查询

设置动态SQL

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.gxt.mapper.EmpMapper">
    <select id="list" resultType="com.gxt.pojo.Emp">
        select *
        from emp
        <where>
            <if test="name!=null and name!=''">
                name like concat('%',#{name},'%')
            </if>
            <if test="gender!=null">
                and gender = #{gender}
            </if>
            <if test="begin!=null and end!=null">
                and entrydate between #{begin} and #{end}
            </if>
        </where>
        order by update_time desc
    </select>
</mapper>
```

**Controller层**

```JAVA
@GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("分页查询,参数:{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
        PageBean pageBean = empService.page(page,pageSize,name,gender,begin,end);
        return Result.success(pageBean);
    }
```

#### 4.6.4 批量删除员工

xml文件

```xml
<delete id="delete">
        delete from emp
        where id
        <foreach collection="ids" item="id" separator="," open="in ( " close=")">
            #{id}
        </foreach>
    </delete>
```

Controller接口

```java
@DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除:{}",ids);
        empService.delete(ids);
        return Result.success();
    }
```

#### 4.6.5 新增员工

##### 4.6.5.1 添加员工

**mapper接口:**

```java
@Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void save(Emp emp);
```

**service层:**

```java
@Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.save(emp);
    }
```

**Controller层:**

```java
@PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工:{}",emp);
        empService.save(emp);
        return Result.success();
    }
```

##### 4.6.5.2 上传文件

**简介:**

![image-20251003212250063](img/image-20251003212250063.png)

![image-20251003212615079](img/image-20251003212615079.png)

**保存文件到本地:**

![image-20251004115523943](img/image-20251004115523943.png)

![image-20251004115750275](img/image-20251004115750275.png)

![image-20251004120149499](img/image-20251004120149499.png)

**阿里云OSS:**

![image-20251004120710994](img/image-20251004120710994.png)

![image-20251004120858443](img/image-20251004120858443.png)

![image-20251004173031705](img/image-20251004173031705.png)

#### 4.6.6 修改员工

**页面回显**

![image-20251004174708050](img/image-20251004174708050.png)

略,和部门相同

![image-20251005183828533](img/image-20251005183828533.png)

### 4.7 springboot配置文件

![image-20251005190018187](img/image-20251005190018187.png)

![image-20251005190544694](img/image-20251005190544694.png)

![image-20251005191007274](img/image-20251005191007274.png)

基本上使用 yml配置文件

![image-20251005191105740](img/image-20251005191105740.png)

![image-20251005191235108](img/image-20251005191235108.png)

![image-20251005221526663](img/image-20251005221526663.png)

![image-20251005221552393](img/image-20251005221552393.png)

![image-20251005221602361](img/image-20251005221602361.png)

## 5. 登录功能

### 5.1 登录校验

![image-20251006222301689](img/image-20251006222301689.png)

![image-20251006222648856](img/image-20251006222648856.png)

#### 5.1.1 会话技术

![image-20251006223649845](img/image-20251006223649845.png)

**Cookie:**

![image-20251007162637667](img/image-20251007162637667.png)

**Session:**

![image-20251007163017509](img/image-20251007163017509.png)

![image-20251007163500621](img/image-20251007163500621.png)

**令牌技术:**

![image-20251007163713638](img/image-20251007163713638.png)

#### 5.1.2 JWT令牌

![image-20251008194636103](img/image-20251008194636103.png)

![image-20251008194756317](img/image-20251008194756317.png)

**生成校验:**

引入依赖:

```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.11.5</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.11.5</version>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId> <!-- or jjwt-gson if Gson is preferred -->
    <version>0.11.5</version>
    <scope>runtime</scope>
</dependency>

```

JWT生成解析:

```java
//秘钥
    String key = "gxttttttttttttttttttttttttttttttttttttttttttttttttttt";
    String Token = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiZ3h0IiwiaWQiOjEsImV4cCI6MTc1OTkzMDIzMH0.dLQKlYMK9iCF92OseooCnarEglNVGjtAqsb1P33hVcQ";
    @Test
    public void testGenJwt(){ //生成令牌

        Map<String, Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("name","gxt");

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,key) //数字签名算法,秘钥
                .setClaims(claims) //自定义数据(载荷)
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) //设置有效期为1小时
                .compact();//返回字符串类型

        System.out.println(jwt);
    }
    @Test
    public void testParseJwt(){ //解析
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(Token)
                .getBody();
        System.out.println(claims);
    }
```

![image-20251008203801966](img/image-20251008203801966.png)

**改造,之前的登录的代码:**

```java
@PostMapping("/login")
public Result login(@RequestBody Emp emp){
    Emp e = empService.login(emp);

    //登录成功,生成令牌,下发令牌
    if(e!=null){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",e.getId());
        claims.put("name",e.getName());
        claims.put("username",e.getUsername());
        String jwt = JwtUtils.generateJwt(claims);
        return Result.success(jwt);
    }

    return  Result.error("用户名或密码错误");
}
```

#### 5.1.3 过滤器Filter

![image-20251008224755903](img/image-20251008224755903.png)

![image-20251009082849225](img/image-20251009082849225.png)

**重写方法可以只写doFilter,另外两个都有默认实现**

```java
//放行
filterChain.doFilter(servletRequest,servletResponse);
```

**Filter执行流程:**

![image-20251009084911451](img/image-20251009084911451.png)

**Filter拦截路径:**

![image-20251009085118470](img/image-20251009085118470.png)

****

**过滤器链:**

![image-20251009085909284](img/image-20251009085909284.png)

**登录校验:**

![image-20251009152239656](img/image-20251009152239656.png)

```java
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.http.HttpRequest;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //强转
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //1.获取请求url
        String url = req.getRequestURL().toString();
        log.info("请求的URL: {}",url);

        //2.判断请求url中是否包含login,如果包含,说明是登录,直接放行
        if(url.contains("login")){
            log.info("登录操作,放行...");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //3.获取请求头的令牌 (token)
        String jwt = req.getHeader("token");

        //4.判断令牌是否存在,如果不存在报错
        if(!StringUtils.hasLength(jwt)){
            log.info("请求为空,返回未登录信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换成json
            String jsonString = JSONObject.toJSONString(error);
            resp.getWriter().write(jsonString);
            return;
        }
        //5.解析token,如果解析失败,返回error
        try {
            JwtUtils.parseJWT(jwt);
        }catch (Exception e){ //解析失败
            e.printStackTrace();
            log.info("解析令牌失败");
            Result error = Result.error("NOT_LOGIN");
            //手动转换成json
            String jsonString = JSONObject.toJSONString(error);
            resp.getWriter().write(jsonString);
            return;
        }
        //6.令牌合法,放行
        filterChain.doFilter(servletRequest,servletResponse);
        log.info("成功");

    }
}

```

#### 5.1.4 拦截器Interceptor

**概述:**

![image-20251009182716050](img/image-20251009182716050.png)

**快速入门**

- 定义拦截器

  ```java
  @Component //交给IOC容器管理
  public class LoginCheckInterceptor implements HandlerInterceptor {
      @Override //目标资源方法运行前运行,返回true:放行,返回false,不放行
      public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
          System.out.println("preHandle .....");
          return true;
      }
  
      @Override //目标资源方法运行后运行
      public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
          System.out.println("postHandle .....");
      }
  
      @Override //视图渲染完毕后运行,最后运行
      public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
          System.out.println("afterCompletion .....");
      }
  }
  ```

- 注册配置

  ```java
  @Configuration //配置类的注释
  public class WebConfig implements WebMvcConfigurer {
  
      @Autowired
      private LoginCheckInterceptor loginCheckInterceptor;
  
      @Override
      public void addInterceptors(InterceptorRegistry registry) {
          registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/**"); //指定加入的拦截器,注册拦截器,后面指定拦截的资源
      }
  }
  ```

  ![image-20251009185841901](img/image-20251009185841901.png)

**拦截路径:**

![image-20251009212443043](img/image-20251009212443043.png)

**执行流程:**

![image-20251009213143918](img/image-20251009213143918.png)

**登录校验**

```java
@Slf4j
@Component //交给IOC容器管理
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override //目标资源方法运行前运行,返回true:放行,返回false,不放行
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {

        //1.获取请求url
        String url = req.getRequestURL().toString();
        log.info("请求的URL: {}",url);

        //2.判断请求url中是否包含login,如果包含,说明是登录,直接放行
        if(url.contains("login")){
            log.info("登录操作,放行...");
            return true;
        }
        //3.获取请求头的令牌 (token)
        String jwt = req.getHeader("token");

        //4.判断令牌是否存在,如果不存在报错
        if(!StringUtils.hasLength(jwt)){
            log.info("请求为空,返回未登录信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换成json
            String jsonString = JSONObject.toJSONString(error);
            resp.getWriter().write(jsonString);
            return false;
        }
        //5.解析token,如果解析失败,返回error
        try {
            JwtUtils.parseJWT(jwt);
        }catch (Exception e){ //解析失败
            e.printStackTrace();
            log.info("解析令牌失败");
            Result error = Result.error("NOT_LOGIN");
            //手动转换成json
            String jsonString = JSONObject.toJSONString(error);
            resp.getWriter().write(jsonString);
            return false;

        }
        log.info(jwt);
        //6.令牌合法,放行
        log.info("成功");
        return true;
    }

    @Override //目标资源方法运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle .....");
    }

    @Override //视图渲染完毕后运行,最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion .....");
    }
}
```

## 6.异常处理

![image-20251010182632024](img/image-20251010182632024.png)

![image-20251010183026907](img/image-20251010183026907.png)

**全局异常处理器:**

![image-20251010183228661](img/image-20251010183228661.png)

```java
/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) //捕获所有异常
    public Result ex(Exception ex){
        ex.printStackTrace();
        return Result.error("对不起,操作失败");
    }
}
```

## 7.事务管理

**概念:**

![image-20251010184653074](img/image-20251010184653074.png)

**spring事务管理:**

![image-20251010191749977](img/image-20251010191749977.png)

![image-20251010191850761](img/image-20251010191850761.png)

![image-20251010192052110](img/image-20251010192052110.png)

```yml
logging:
  level:
    org.springframework.jdbc.support.JdbcTransactionManger: debug
```

**事务进阶:**

![image-20251010193215979](img/image-20251010193215979.png)

![image-20251010193635970](img/image-20251010193635970.png)

![image-20251010193840034](img/image-20251010193840034.png)

required

requires_new

这两个常用

![image-20251010195000258](img/image-20251010195000258.png)

## 8.AOP

### 8.1 AOP 基础

![image-20251010213929128](img/image-20251010213929128.png)

**快速入门:**

做一个可以统计各个方法执行时间的程序



- 首先导入依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

- AOP程序

```java
@Component
@Aspect
@Slf4j
public class TimeAspect {
    @Around("execution(* com.gxt.service.*.*(..))") //所以接口所有类，切入点表达式
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        //1.记录开始时间
        long begin = System.currentTimeMillis();

        //2.调用原始方法运行
        Object result = joinPoint.proceed();

        //3.记录结束时间
        long end = System.currentTimeMillis();

        //getSignature() 拿到方法签名
        log.info(joinPoint.getSignature() + "方法执行耗时： {}ms",end - begin);

        return result;
    }
}
```

- 结果

![image-20251010215818003](img/image-20251010215818003.png)

- 场景

![image-20251010215942929](img/image-20251010215942929.png)

<u>AOP就是基于动态代理的，动态代理就是函数套函数，把核心逻辑和其他业务分开，最后套起来（自己的理解）</u>

- **静态代理**就像你为明星A雇了一个专属经纪人A，为明星B雇了一个专属经纪人B。
- **动态代理**就像一家经纪公司（`InvocationHandler`），它有一个标准流程（`invoke`方法），可以为公司旗下**任何明星**（任何实现了接口的对象）提供经纪服务。

**核心概念:**

![image-20251011113911361](img/image-20251011113911361.png)

### 8.2 AOP进阶

#### 8.2.1 通知

- **通知类型**

![image-20251011114951257](img/image-20251011114951257.png)

![image-20251011120807968](img/image-20251011120807968.png)

- **通知顺序**

  

![image-20251011145115436](img/image-20251011145115436.png)

#### 8.2.2 切入点表达式

![image-20251011150355556](img/image-20251011150355556.png)

![image-20251011150511831](img/image-20251011150511831.png)

![image-20251011152837623](img/image-20251011152837623.png)

![image-20251011153002485](img/image-20251011153002485.png)

![image-20251011153227625](img/image-20251011153227625.png)

自己写个注解用于标识就行

#### 8.2.3 连接点

![image-20251011155048591](img/image-20251011155048591.png)

### 8.3 AOP案例(记录日志)

![image-20251011160243770](img/image-20251011160243770.png)

- **anno 自定义注解**

```java
//仅起到标识作用
@Retention(RetentionPolicy.RUNTIME) //在运行时候启动
@Target(ElementType.METHOD)//当前注解能作用在哪里
public @interface Log {}
```

- **LogAspect**

```java
@Component
@Slf4j
@Aspect //切面类
public class LogAspect {

    @Autowired
    private HttpServletRequest request; //拿到请求

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.gxt.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //操作人ID - 当前登录员工ID -> 获取请求头的JWT令牌,解析令牌
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        log.info("{}",claims);
        Integer OperateUserId = (Integer) claims.get("id");

        //当前时间
        LocalDateTime now = LocalDateTime.now();

        //操作类名
        String className = joinPoint.getTarget().getClass().getName();

        //操作方法名
        String methodName = joinPoint.getSignature().getName();

        //操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        //方法返回值
        //调用原始目标方法运行
        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed(args);
        long end = System.currentTimeMillis();
        String resultValue = JSONObject.toJSONString(result);

        //操作耗时
        long costTime = end - begin;

        //记录操作日志
        OperateLog operateLog = new OperateLog(
                null,
                OperateUserId,
                now,
                className,
                methodName,
                methodParams,
                resultValue,
                costTime);
        operateLogMapper.insert(operateLog);

        log.info("日志: {}",operateLog);
        return result;
    }
}
```

## 9.SpringBoot原理

### 9.1 配置优先级

![image-20251011203132831](img/image-20251011203132831.png)

![image-20251011203626734](img/image-20251011203626734.png)

![image-20251011204047208](img/image-20251011204047208.png)

### 9.2 Bean管理

#### 9.2.1 Bean的获取

![image-20251011204333486](img/image-20251011204333486.png)

![image-20251011205544667](img/image-20251011205544667.png)![image-20251011205724894](img/image-20251011205724894.png)

#### 9.2.2 Bean的作用域

**Spring boot 项目中的Bean默认是单例的**

![image-20251011210241360](img/image-20251011210241360.png)

延迟初始化注解@LAZY

![image-20251012000239555](img/image-20251012000239555.png)

#### 9.2.3 第三方Bean

![image-20251012000800424](img/image-20251012000800424.png)

![image-20251012001246294](img/image-20251012001246294.png)

![image-20251012001337799](img/image-20251012001337799.png)

### 9.3 springboot 原理

#### 9.3.1 起步依赖

![image-20251012104304154](img/image-20251012104304154.png)

#### 9.3.2 自动配置

![image-20251012105939876](img/image-20251012105939876.png)

- **获取第三方工具类下的Bean的方案:**

![image-20251012115416017](img/image-20251012115416017.png)

因为默认Springboot项目只扫描当前包

![image-20251012120152577](img/image-20251012120152577.png)

ImportSelect:

![image-20251012115945798](img/image-20251012115945798.png)

加载多种类

一般不用自己写,第三方会自己写好,大概是@Enablexxxx名字

------

**原理:**

![image-20251012122052998](img/image-20251012122052998.png)

![image-20251012122557233](img/image-20251012122557233.png)

@Conditonal注解:

![image-20251012141215650](img/image-20251012141215650.png)

![image-20251012142636686](img/image-20251012142636686.png)

#### 9.3.4 自定义starter

![image-20251012150427641](img/image-20251012150427641.png)

![image-20251012150723836](img/image-20251012150723836.png)

![image-20251012152946746](img/image-20251012152946746.png)

## 10.web后端总结

![image-20251012153157607](img/image-20251012153157607.png)

## 11.Maven 高级

### 11.1 分模块设计与开发

![image-20251012153845788](img/image-20251012153845788.png)

![image-20251012154209204](img/image-20251012154209204.png)

### 11.2 继承与聚合

#### 11.2.1 继承

![image-20251012165425063](img/image-20251012165425063.png)

![image-20251012172937123](img/image-20251012172937123.png)

![image-20251012171441574](img/image-20251012171441574.png)

![image-20251012172708548](img/image-20251012172708548.png)

![image-20251012172739962](img/image-20251012172739962.png)

![image-20251012172855885](img/image-20251012172855885.png)

------

**版本锁定**

![image-20251012173833666](img/image-20251012173833666.png)

![image-20251012173932144](img/image-20251012173932144.png)

![image-20251012190937461](img/image-20251012190937461.png)

![image-20251012191007266](img/image-20251012191007266.png)

#### 11.2.2 聚合

![image-20251012191854291](img/image-20251012191854291.png)

![image-20251012192124034](img/image-20251012192124034.png)

![image-20251012192340231](img/image-20251012192340231.png)

### 11.3 私服

![image-20251012193554446](img/image-20251012193554446.png)

![image-20251012194906730](img/image-20251012194906730.png)

**central是中央仓库拿过来的依赖**

![image-20251012195018021](img/image-20251012195018021.png)

![image-20251012195037647](img/image-20251012195037647.png)

![image-20251012195047460](img/image-20251012195047460.png)

![image-20251012195238577](img/image-20251012195238577.png)

![image-20251012195716278](img/image-20251012195716278.png)
