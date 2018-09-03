package com.melody.service;


import org.apache.ibatis.annotations.Param;

import java.util.List;


/** 
 * 底层基本的dao的接口 
 *  
 * @param <T>
 */
public interface BaseMapper<T>  {

    /** 
     * 根据条件集合进行分页查询 
     *  
     * @param t
     *            查询条件 
     * @param currentPage 
     *            当前页数 
     * @param pageSize 
     *            页面大小 
     * @return 返回Pager对象 
     */
    public List<T> queryPage(@Param("object") T t, @Param(value = "currentPage") Integer currentPage,
                             @Param(value = "pageSize") Integer pageSize);


    /**
     * 根据条件进行数量的查询
     *
     * @param t
     * @return 返回符合条件的泛型参数对应表中的数量
     */
    public int count(T t);
  
}