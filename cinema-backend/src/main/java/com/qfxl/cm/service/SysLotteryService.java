package com.qfxl.cm.service;

import com.qfxl.cm.domain.SysBill;

public interface SysLotteryService {
    SysBill drawMovieSession(Long userId, Integer type);

    /**
     * 随机抽取一场电影场次并生成订单
     * @param userId 用户ID
     * @return 抽奖结果订单
     */
   // SysBill drawMovieSession(Long userId);
}