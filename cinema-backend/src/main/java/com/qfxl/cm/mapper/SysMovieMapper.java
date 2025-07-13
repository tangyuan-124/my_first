package com.qfxl.cm.mapper;

import com.qfxl.cm.domain.SysMovie;
import com.qfxl.cm.domain.vo.SysMovieVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 电影持久层
 *
 * @author: qfxl
 * @date: 2023-12-11
 */
@Mapper
public interface SysMovieMapper {

    List<SysMovie> findAllMovies(SysMovieVo sysMovieVo);

    SysMovie findMovieById(Long id);

    SysMovie findOneMovie(Long id);

    int addMovie(SysMovie sysMovie);

    int updateMovie(SysMovie sysMovie);

    int deleteMovie(Long id);

    List<SysMovie> findMovieByCinemaId(Long id);

    List<SysMovie> totalBoxOfficeList();

    List<SysMovie> domesticBoxOfficeList();

    List<SysMovie> foreignBoxOfficeList();

}
