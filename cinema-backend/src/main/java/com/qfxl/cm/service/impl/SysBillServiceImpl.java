package com.qfxl.cm.service.impl;

import com.qfxl.cm.domain.SysBill;
import com.qfxl.cm.mapper.SysBillMapper;
import com.qfxl.cm.service.SysBillService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单服务层实现类
 *
 * @author: qfxl
 * @date: 2023-12-11
 */
@Service
public class SysBillServiceImpl implements SysBillService {

    @Resource
    private SysBillMapper sysBillMapper;

    @Override
    public List<SysBill> findAllBills(SysBill sysBill) {
        return sysBillMapper.findAllBills(sysBill);
    }

    @Override
    public SysBill findBillById(Long id) {
        return sysBillMapper.findBillById(id);
    }

    @Override
    public Object addBill(SysBill sysBill) {
        int rows = sysBillMapper.addBill(sysBill);
        return rows > 0 ? sysBill : rows;
    }

    @Override
    public int updateBill(SysBill sysBill) {
        return sysBillMapper.updateBill(sysBill);
    }

    @Override
    public int deleteBill(Long[] ids) {
        int rows = 0;
        for (Long id : ids) {
            rows += sysBillMapper.deleteBill(id);
        }
        return rows;
    }

    @Override
    public List<SysBill> findTimeoutBill() {
        return sysBillMapper.findTimeoutBill();
    }

}
