package com.qfxl.cm.service;

import com.qfxl.cm.domain.SysMovie;
import com.qfxl.cm.domain.vo.SysMovieVo;

import java.util.List;

/**
 * 电影服务层

 */
public interface SysMovieService {

    List<SysMovie> findAllMovies(SysMovieVo sysMovieVo);

    SysMovie findMovieById(Long id);

    SysMovie findOneMovie(Long id);

    int addMovie(SysMovie sysMovie);

    int updateMovie(SysMovie sysMovie);

    int deleteMovie(Long[] ids);

    List<SysMovie> totalBoxOfficeList();

    List<SysMovie> domesticBoxOfficeList();

    List<SysMovie> foreignBoxOfficeList();

}
