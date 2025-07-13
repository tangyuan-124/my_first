package com.qfxl.cm.service.impl;

import com.qfxl.cm.domain.SysCinema;
import com.qfxl.cm.mapper.SysCinemaMapper;
import com.qfxl.cm.service.SysCinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 电影院服务层接口实现类
 *
 * @author: qfxl
 * @date: 2023-12-11
 */
@Service
public class SysCinemaServiceImpl implements SysCinemaService {

    @Autowired
    private SysCinemaMapper sysCinemaMapper;

    @Override
    public SysCinema findCinema() {
        return sysCinemaMapper.findCinema();
    }

    @Override
    public int updateCinema(SysCinema sysCinema) {
        return sysCinemaMapper.updateCinema(sysCinema);
    }

    @Override
    public SysCinema findCinemaById(Long id) {
        return sysCinemaMapper.findCinemaById(id);
    }
}
