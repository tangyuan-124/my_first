package com.qfxl.cm.service;

import com.qfxl.cm.domain.SysSession;
import com.qfxl.cm.domain.vo.SysSessionVo;

import java.util.List;

/**
 * 电影场次服务层
 *
 * @author: qfxl
 * @date: 2023-12-11
 */
public interface SysSessionService {

    List<SysSession> findByVo(SysSessionVo sysSessionVo);

    List<SysSession> findSessionByMovieIdOrHallId(SysSession sysSession);

    SysSession findSessionById(Long id);

    SysSession findOneSession(Long id);

    int addSession(SysSession sysSession);

    int updateSession(SysSession sysSession);

    int deleteSession(Long[] id);

    List<SysSession> findSessionByMovieId(Long movieId);

}
