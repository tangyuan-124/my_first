package com.qfxl.cm.service;

import com.qfxl.cm.domain.SysCinema;


/**
 * 电影院服务层接口
 *
 * @author: qfxl
 * @date: 2023-12-11
 */
public interface SysCinemaService {

    SysCinema findCinema();

    int updateCinema(SysCinema sysCinema);

    SysCinema findCinemaById(Long id);

}
