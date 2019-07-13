package com.email.support.enums;

/**
 * 用户优先级
 *
 * @author suiguozhen
 * @date 19/07/13
 */
public enum UserPriorityEnums {
    /**
     * 优先级别
     */
    LOW(0, "低级别"),
    NORMAL(1, "一般级别"),
    TOP(2, "最高级别");

    private Integer priority;
    private String label;

    UserPriorityEnums(Integer priority, String label){
        this.priority = priority;
        this.label = label;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
