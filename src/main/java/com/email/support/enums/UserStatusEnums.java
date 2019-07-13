package com.email.support.enums;

/**
 * 用户发送状态
 * @author suiguozhen
 * @date 19/07/13
 */
public enum UserStatusEnums {
    /**
     * 用户发送状态
     */
    UNKNOWN(0, "未知"),
    QUEUE(1, "入队"),
    SUCCESS(2, "成功"),
    FAILED(3, "失败");

    private Integer ordinal;
    private String label;

    UserStatusEnums(Integer ordinal, String label) {
        this.ordinal = ordinal;
        this.label = label;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public String getLabel() {
        return label;
    }
}
