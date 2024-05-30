package com.edison.framework.core;

/**
 * redis key
 * @author edison
 */
public class RedisKeys {

    /**
     * 登录用户信息
     * @param loginId userId
     * @return 用户信息key
     */
    public static String loginInfoKey(String loginId) {
        return "sys:login-info:" + loginId;
    }
}
