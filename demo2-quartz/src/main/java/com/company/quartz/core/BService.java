package com.company.quartz.core;


import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public interface BService<T> {

    Boolean insertSelective(T t);

    int insertList(List<T> t);

    Boolean updateById(T t);

    List<T> query(T t);

    T queryById(Object id);

    List<T> queryByIds(String ids); //eg：ids -> “1,2,3,4”

    List<T> queryBy(String fieldName, Object value);

    Integer queryCount(T t);

    List<T> queryByCondition(Condition condition);

    List<T> queryByExample(Example example);

    int updateByExample(T t, Example example);

    int updateByCondition(T t, Condition condition);
}
