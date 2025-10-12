package com.gxt.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//仅起到标识作用
@Retention(RetentionPolicy.RUNTIME) //在运行时候启动
@Target(ElementType.METHOD)//当前注解能作用在哪里
public @interface Log {}
