package com.edison.framework.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author edison
 */
@Getter
@AllArgsConstructor
public enum OperTypeEnum {

    CREATE(1, "创建"),
    MODIFY(2, "修改"),
    REMOVE(3, "删除"),
    QUERY(4, "查询"),
    LOGIN(5, "登录"),
    OTHER(0, "其它");

    private int value;
    private String description;
}
