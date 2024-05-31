package com.edison.framework.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

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
        return new ResponseRecord(HttpStatus.OK.value(), null, data);
    }

    public static <T> ResponseRecord fail() {
        return ResponseRecord.fail(HttpStatus.BAD_REQUEST.value(), null);
    }

    public static <T> ResponseRecord fail(int code, String msg) {
        return new ResponseRecord(code, msg, null);
    }
}
