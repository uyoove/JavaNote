# JavaWeb

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