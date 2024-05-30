package com.edison.framework.exception;

import lombok.Getter;

/**
 * 业务异常
 * @author edison
 */
@Getter
public class BizException extends RuntimeException {

    public BizException(String message) {
        super(message);
    }
}
