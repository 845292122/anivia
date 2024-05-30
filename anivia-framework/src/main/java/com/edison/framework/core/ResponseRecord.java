package com.edison.framework.core;

import cn.hutool.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * http结果
 * @author edison
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResponseRecord<T> (int code, String msg, T data) {

    public static <T> ResponseRecord ok() {
        return ResponseRecord.ok(null);
    }

    public static <T> ResponseRecord ok(T data) {
        return new ResponseRecord(HttpStatus.HTTP_OK, null, data);
    }

    public static <T> ResponseRecord fail() {
        return ResponseRecord.fail(HttpStatus.HTTP_BAD_REQUEST, null);
    }

    public static <T> ResponseRecord fail(int code, String msg) {
        return new ResponseRecord(code, msg, null);
    }
}
