package com.wl.multipledatasources.config;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/19 15:46
 */
public class DataSourceHolder {

    private static ThreadLocal<Integer> context = new ThreadLocal<>();

    //0:写，1:读
    public static DbEnum get(){
        Integer type = context.get();
        return type == null || type % 2 ==0 ? DbEnum.MASTER : DbEnum.slave();
    }

    public static void set(Integer type){
        context.set(type);
    }

}
