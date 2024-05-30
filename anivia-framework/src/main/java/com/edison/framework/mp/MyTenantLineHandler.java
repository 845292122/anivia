package com.edison.framework.mp;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * mp多租户插件
 * @author edison
 */
public class MyTenantLineHandler implements TenantLineHandler {

    private final static Set<String> IGNORE_TABLES = Stream.of("sys_log", "sys_menu", "sys_user", "sys_user_menu")
            .collect(Collectors.toSet());

    @Override
    public Expression getTenantId() {
        String loginId = StpUtil.getLoginIdAsString();
        return new LongValue(loginId);
    }

    @Override
    public String getTenantIdColumn() {
        return "owner";
    }

    @Override
    public boolean ignoreTable(String tableName) {
        return IGNORE_TABLES.contains(tableName);
    }

    

}
