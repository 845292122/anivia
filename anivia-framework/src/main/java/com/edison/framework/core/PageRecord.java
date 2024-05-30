package com.edison.framework.core;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * 分页结果
 * @author edison
 */
public record PageRecord<T> (long total, List<T> records) {

    public static <T> PageRecord build(List<T> records) {
        long total = records.size();
        return new PageRecord(total, records);
    }

    public static <T> PageRecord build(Page<T> page) {
        return new PageRecord(page.getTotal(), page.getRecords());
    }

    public static <T> PageRecord build(long total, List<T> records) {
        return new PageRecord(total, records);
    }
}
