package com.gxt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页查询结果封装类
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageBean {
    private long total;
    private List rows;
}
