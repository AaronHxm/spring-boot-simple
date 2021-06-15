package com.simple.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author aaron.hu
 * @version 1.0.0
 * @ClassName RestResult.java
 * @Description TODO
 * @createTime 2021年05月27日 09:46:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("")
public class RestResult<T> implements Serializable {
    private static final long serialVersionUID = 8760469908904386583L;
    @ApiModelProperty("状态码：1正常，其它异常")
    private Integer code;

    @ApiModelProperty("返回信息")
    private String message;

    @ApiModelProperty("返回数据主体")
    private T datas;

    /**
     *
     * {
     *     a:"xxxxxx",
     *     array:[
     *     {
     *
     *     }
     *     ]
     *
     *
     * }
     *
     *
     *
     * @param code
     * @param message
     * @param datas
     * @param <T>
     * @return
     */

//    private boolean success = true;
//    private int exitCode;
//
//    private T data;

    public static <T> RestResult<T> of(Integer code, String message, T datas) {
        return new RestResult<>(code, message, datas);
    }

    public static <T> RestResult<T> success(String message) {
        return of(ResultCodeEnum.SUCCESS.getCode(), message, null);
    }

    public static <T> RestResult<T> success(T datas) {
        return of(ResultCodeEnum.SUCCESS.getCode(), "操作成功", datas);
    }

    public static <T> RestResult<T> success(String message, T datas) {
        return of(ResultCodeEnum.SUCCESS.getCode(), message, datas);
    }

    public static <T> RestResult<T> failed(String message) {
        return of(ResultCodeEnum.ERROR.getCode(), message, null);
    }

    public static <T> RestResult<T> failed(Integer code, String message) {
        return of(code, message, null);
    }

    public static <T> RestResult<T> failed(String message, T datas) {
        return of(ResultCodeEnum.ERROR.getCode(), message, datas);
    }

    public static <T> RestResult<T> failed(Integer code, String message, T datas) {
        return of(code, message, datas);
    }
}
