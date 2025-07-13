package com.qfxl.cm.service;

import com.qfxl.cm.domain.SysBill;

import java.util.List;

/**
 * 订单服务层接口
 *
 * @author: qfxl
 * @date: 2023-12-11
 */
public interface SysBillService {

    List<SysBill> findAllBills(SysBill sysBill);

    SysBill findBillById(Long id);

    Object addBill(SysBill sysBill);

    int updateBill(SysBill sysBill);

    int deleteBill(Long[] ids);

    List<SysBill> findTimeoutBill();

}
