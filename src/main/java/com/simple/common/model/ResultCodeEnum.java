package com.simple.common.model;

/**
 * @author aaron.hu
 * @version 1.0.0
 * @ClassName ResultCodeEnum.java
 * @Description TODO
 * @createTime 2021年05月27日 10:31:00
 */
public enum ResultCodeEnum {
    SUCCESS(200, "成功"), ERROR(-1, "失败");

    private Integer code;
    private String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
