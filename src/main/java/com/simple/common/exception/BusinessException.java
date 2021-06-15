package com.simple.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 业务异常
 *
 * @Author aaron.hu
 */
@Getter
@Setter
public class BusinessException extends ApplicationException {
    public BusinessException(String message) {
        super(message);
        this.code = -1;
    }

    public BusinessException(Integer code, String message) {
        super(code, message);
    }

    public BusinessException(Integer code, String message,Object data) {
        super(code, message,data);
    }
}
