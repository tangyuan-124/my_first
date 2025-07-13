package com.qfxl.cm.service.impl;

import com.qfxl.cm.domain.SysHall;
import com.qfxl.cm.mapper.SysHallMapper;
import com.qfxl.cm.service.SysHallService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 观影排座服务层实现类
 *
 * @author: qfxl
 * @date: 2023-12-11
 */
@Service
public class SysHallServiceImpl implements SysHallService {

    @Resource
    private SysHallMapper sysHallMapper;

    @Override
    public List<SysHall> findAllHalls(SysHall sysHall) {
        return sysHallMapper.findAllHalls(sysHall);
    }

    @Override
    public SysHall findHallById(SysHall sysHall) {
        return sysHallMapper.findHallById(sysHall);
    }

    @Override
    public int addHall(SysHall sysHall) {
        return sysHallMapper.addHall(sysHall);
    }

    @Override
    public int updateHall(SysHall sysHall) {
        return sysHallMapper.updateHall(sysHall);
    }

    @Override
    public int deleteHall(SysHall[] sysHalls) {
        int rows = 0;
        for (SysHall sysHall : sysHalls) {
            rows += sysHallMapper.deleteHall(sysHall);
        }
        return rows;
    }
}
