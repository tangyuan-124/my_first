package com.qfxl.cm.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 封装电影场次的条件
 *
 * @author: qfxl
 * @date: 2023-12-11
 */
@Data
public class SysSessionVo implements Serializable {

    private Long hallId;

    private Long movieId;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private LocalDate sessionDate;

    public SysSessionVo() {
    }

    public SysSessionVo(Long hallId, Long movieId, LocalDate sessionDate) {
        this.hallId = hallId;
        this.movieId = movieId;
        this.sessionDate = sessionDate;
    }

}
