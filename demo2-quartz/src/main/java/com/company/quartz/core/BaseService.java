package com.company.quartz.core;

import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 基础实现类,根据AbstractService 做了简单的调整
 * 2019-4-23
 * author ccx
 * @param <M>
 * @param <T>
 */
public abstract class BaseService<M extends Mapper<T>,T> implements BService<T>{

    @Autowired
    protected M baseMapper;

    private Class<T> modelClass;  // 当前泛型真实类型的Class

    public BaseService(){
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[1];
    }

    public Boolean insertSelective(T t){
        return baseMapper.insertSelective(t)>0;
    }

    public int insertList(List<T> tlist) {
        return baseMapper.insertList(tlist);
    }

    public List<T> query(T t){
        return baseMapper.select(t);
    }

    public List<T> queryBy(String fieldName,Object value){
        try {
            T model = modelClass.newInstance();
            Field field = modelClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(model, value);
            return baseMapper.select(model);
        } catch (InstantiationException  | IllegalAccessException  | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer queryCount(T t) {
        return baseMapper.selectCount(t);
    }

    public List<T> queryByCondition(Condition condition){
        return baseMapper.selectByCondition(condition);
    }

    public List<T> queryByExample(Example example){
        return baseMapper.selectByExample(example);
    }

    public T queryById(Object id){
        return baseMapper.selectByPrimaryKey(id);
    }

    public List<T> queryByIds(String ids) {
        return baseMapper.selectByIds(ids);
    }

    public Boolean updateById(T t){
        return baseMapper.updateByPrimaryKeySelective(t)>0;
    }

    public int updateByExample(T t, Example example) {
        return baseMapper.updateByExampleSelective(t,example);
    }

    public int updateByCondition(T t, Condition condition) {
        return baseMapper.updateByConditionSelective(t,condition);
    }
}
