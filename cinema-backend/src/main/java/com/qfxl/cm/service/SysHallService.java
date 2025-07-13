package com.qfxl.cm.service;

import com.qfxl.cm.domain.SysHall;

import java.util.List;

/**
 * 观影排座服务层
 *
 * @author: qfxl
 * @date: 2023-12-11
 */
public interface SysHallService {

    List<SysHall> findAllHalls(SysHall sysHall);

    SysHall findHallById(SysHall sysHall);

    int addHall(SysHall sysHall);

    int updateHall(SysHall sysHall);

    int deleteHall(SysHall[] sysHall);
}
