package com.simple.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 参数异常
 *
 * @Author aaron.hu
 */
@Getter
@Setter
public class ParameterException extends ApplicationException {
    private Integer code;

    public ParameterException(String message) {
        super(message);
        this.code = -1;
    }

    public ParameterException(Integer code, String message) {
        super(code, message);
    }
}
