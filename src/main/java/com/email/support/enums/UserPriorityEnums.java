package com.email.support.enums;

import java.util.Objects;

/**
 * 用户优先级
 *
 * @author suiguozhen
 * @date 19/07/13
 */
public enum UserPriorityEnums {
    /**
     * 优先级别
     * LOW      延迟队列
     * NORMAL   一般队列
     * TOP      高级队列
     */
    LOW(0, "低级别"),
    NORMAL(1, "一般级别"),
    TOP(2, "最高级别");

    private Integer ordinal;
    private String label;

    UserPriorityEnums(Integer ordinal, String label) {
        this.ordinal = ordinal;
        this.label = label;
    }

    /**
     * 根据传入的priority返回枚举
     *
     * @param priority 优先级
     * @return UserPriorityEnums
     */
    public static UserPriorityEnums getByPriority(Integer priority) {
        if (Objects.isNull(priority)) {
            return UserPriorityEnums.LOW;
        }
        for (UserPriorityEnums enums : UserPriorityEnums.values()) {
            if (Integer.compare(enums.ordinal, priority) > -1) {
                return enums;
            }
        }
        return UserPriorityEnums.LOW;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
