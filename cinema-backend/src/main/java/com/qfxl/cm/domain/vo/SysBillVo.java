package com.qfxl.cm.domain.vo;

import com.qfxl.cm.domain.SysBill;
import lombok.Data;

import java.io.Serializable;

/**
 * 接收前端增加订单的数据
 *
 * @author: qfxl
 * @date: 2023-12-11
 */
@Data
public class SysBillVo implements Serializable {

    //订单信息
    private SysBill sysBill;

    //若成功更新后场次的座位信息
    private String sessionSeats;

}
