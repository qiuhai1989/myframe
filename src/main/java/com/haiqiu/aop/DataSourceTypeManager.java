package com.haiqiu.aop;

import com.haiqiu.constant.DataSources;

/**
 * Created by Administrator on 2016/4/2.
 */
public class DataSourceTypeManager {
    /**
     * 通过 TheadLocal 来保存每个线程选择哪个数据源的标志(key)
     */
    private static final ThreadLocal<DataSources> dataSourceTypes = new ThreadLocal<DataSources>();

    public static DataSources get(){
        System.out.println("get:"+Thread.currentThread().getName());
        return  dataSourceTypes.get();
    }

    public static void set(DataSources dataSourceType){
        System.out.println("set:"+Thread.currentThread().getName());
        dataSourceTypes.set(dataSourceType);
    }

    public static void reset(){
        System.out.println("reset:"+Thread.currentThread().getName());
        dataSourceTypes.set(DataSources.MASTER);
    }

}
