package com.melody.service;

import java.io.Serializable;

/**
 * Created by liuyw on 2015/11/20.
 */
public interface BaseService<T, PK extends Serializable> {

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
    public Pager<T> queryPage(T t, Integer currentPage,
                              Integer pageSize);
    /**
     * 根据条件集合进行分页查询
     *
     * @param t
     *            查询条件
     * @return 返回Pager对象
     */
   // public Pager<T> queryPage(T t, Pager<T> pager);
}
