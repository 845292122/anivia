package com.edison.framework.core;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;

import java.util.Date;

/**
 * @author edison
 */
public class BaseDO {

    /**
     * 创建者ID
     */
    @TableField(value = "creator_id", fill = FieldFill.INSERT)
    private Long creatorId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新者ID
     */
    @TableField(value = "updater_id", fill = FieldFill.UPDATE, updateStrategy = FieldStrategy.NOT_NULL)
    private Long updaterId;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 逻辑删除(0:未删除 1:已删除)
     */
    @TableLogic
    @TableField(value = "deleted")
    private Integer deleted;
}
