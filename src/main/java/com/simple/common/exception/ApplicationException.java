package com.simple.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author aaron.hu
 * @date: 2020-04-12 21:33
 **/
@Getter
@Setter
public class ApplicationException extends Exception {
    protected Integer code;
    protected Object data;

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(Integer code, String message) {
        super(message);
        this.code = code;
    }
    public ApplicationException(Integer code, String message,Object data) {
        super(message);
        this.code = code;
        this.data = data;
    }
}
