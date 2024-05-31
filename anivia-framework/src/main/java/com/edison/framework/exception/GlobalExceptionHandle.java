package com.edison.framework.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.edison.framework.core.ResponseRecord;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author edison
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandle {

    /**
     * 未登录
     */
    @ExceptionHandler(NotLoginException.class)
    public ResponseRecord<Void> handleNotLoginException(NotLoginException e) {
        log.error("登录凭证过期: {}", e.getMessage());
        return ResponseRecord.fail(HttpStatus.UNAUTHORIZED.value(), "登录凭证过期.");
    }

    /**
     * 角色权限异常
     */
    @ExceptionHandler(NotRoleException.class)
    public ResponseRecord<Void> handleNotRoleException(NotRoleException e) {
        log.error("角色权限异常: {}", e.getMessage());
        return ResponseRecord.fail(HttpStatus.FORBIDDEN.value(), "角色权限不足,请联系管理员");
    }

    /**
     * 操作权限异常
     */
    @ExceptionHandler(NotPermissionException.class)
    public ResponseRecord<Void> handleNotPermissionException(NotPermissionException e) {
        log.error("操作权限异常: {}", e.getMessage());
        return ResponseRecord.fail(HttpStatus.FORBIDDEN.value(), "操作权限不足,请联系管理员");
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseRecord<Void> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                          HttpServletRequest request) {
        log.error("请求地址'{}',不支持'{}'请求", request.getRequestURI(), e.getMethod());
        return ResponseRecord.fail(HttpStatus.METHOD_NOT_ALLOWED.value(), e.getMessage());
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BizException.class)
    public ResponseRecord<Void> handleServiceException(BizException e) {
        return ResponseRecord.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    /**
     * 请求参数异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseRecord<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseRecord.fail(HttpStatus.BAD_REQUEST.value(), message);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseRecord<Void> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String errorMsg = String.format("请求地址'%s',发生未知异常.", request.getRequestURI());
        log.error(errorMsg, e);
        return ResponseRecord.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseRecord<Void> unknownException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseRecord.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}
