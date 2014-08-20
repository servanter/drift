package com.zhy.drift.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractDAO<E> {

    /**
     * 将Map封装成实体类
     * 
     * @param map
     * @param bean
     * @return
     */
    public abstract E map2Bean(Map<String, Object> map);

    /**
     * 将List封装成实体
     * 
     * @param list
     * @return
     */
    public abstract List<E> list2BeanList(List<Map<String, Object>> list);

    /**
     * 将参数封装成数组
     * @param args
     * @return
     */
    public Object[] parameters2Array(Object... args) {
        List<Object> parameters = new ArrayList<Object>();
        for (int i = 0; i < args.length; i++) {
            parameters.add(args[i]);
        }
        return parameters.toArray();
    }
}
