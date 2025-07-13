package com.qfxl.cm.mapper;

import com.qfxl.cm.domain.SysBill;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 订单持久层
 *
 * @author: qfxl
 * @date: 2023-12-11
 */
@Mapper
public interface SysBillMapper {

    List<SysBill> findAllBills(SysBill sysBill);

    SysBill findBillById(Long id);

    int addBill(SysBill sysBill);

    int updateBill(SysBill sysBill);

    int deleteBill(Long id);

    List<SysBill> findTimeoutBill();

}
