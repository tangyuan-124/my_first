package com.qfxl.cm.mapper;

import com.qfxl.cm.domain.SysCinema;
import org.apache.ibatis.annotations.Mapper;

/**
 * 影院持久层
 *
 * @author: qfxl
 * @date: 2023-12-11
 */
@Mapper
public interface SysCinemaMapper {

    SysCinema findCinema();

    int updateCinema(SysCinema sysCinema);

    SysCinema findCinemaById(Long id);

}
