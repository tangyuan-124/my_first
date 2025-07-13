package com.qfxl.cm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qfxl.cm.common.page.Page;
import com.qfxl.cm.common.page.PageBuilder;
import com.qfxl.cm.common.response.R;

import java.util.List;

/**
 * 抽取重复功能为基类
 *
 * @author: qfxl
 * @date: 2023-12-11
 */
public class BaseController {

    /**
     * 开启分页
     */
    public void startPage() {
        Page page = PageBuilder.buildPage();
        Integer pageNum = page.getPageNum();
        Integer pageSize = page.getPageSize();
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize, page.getOrderByColumn());
        }
    }

    /**
     * 根据修改行数返回响应消息
     *
     * @param rows
     * @return
     */
    public R getResult(int rows) {
        return rows == 0 ? R.error() : R.success();
    }

    /**
     * 分页响应消息
     *
     * @param data
     * @return
     */
    public R getResult(List<?> data) {
        PageInfo pageInfo = new PageInfo(data);
        R r = R.success(data);
        r.put(PageBuilder.PAGE_NUM, pageInfo.getPageNum());
        r.put(PageBuilder.PAGE_SIZE, pageInfo.getPageSize());
        r.put(PageBuilder.TOTAL, pageInfo.getTotal());
        return r;
    }

    /**
     * 对象类型响应消息
     *
     * @param data
     * @return
     */
    public R getResult(Object data) {
        return R.success(data);
    }

}
