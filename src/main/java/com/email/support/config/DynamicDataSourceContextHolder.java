package com.email.support.config;

import java.util.Objects;

/**
 * 数据源本地线程操作
 * @author suiguozhen
 * @date 19/07/06
 */
public class DynamicDataSourceContextHolder {

    private static final ThreadLocal<DatasourseType> CONTEXT_HOLDER = new ThreadLocal<>();

    public enum DatasourseType{
        /**
         * 数据源类型
         */
        MASTER,
        SLAVE;

    }

    /**
     * 设置本地线程变量
     * @param datasourseType 数据类型
     */
    public static void setContextHolder(DatasourseType datasourseType){
        CONTEXT_HOLDER.set(datasourseType);
    }

    /**
     * 获取本地线程变量的值 默认返回主数据源
     * @return DatasourseType
     */
    public static DatasourseType getContextHolder(){
        DatasourseType datasourseType = CONTEXT_HOLDER.get();
        return Objects.isNull(datasourseType) ? DatasourseType.MASTER :datasourseType;
    }

    /**
     * 清空本地线程变量
     */
    public static void removeDataSourceType(){
        CONTEXT_HOLDER.remove();
    }

}
