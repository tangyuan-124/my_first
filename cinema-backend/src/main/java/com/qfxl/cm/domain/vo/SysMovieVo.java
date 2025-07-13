package com.qfxl.cm.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * 封装电影查询的条件
 *
 * @author: qfxl
 * @date: 2023-12-11
 */
@Data
public class SysMovieVo implements Serializable {

    private String movieName;

    private String movieArea;

    private Long movieCategoryId;

    private Date startDate;

    private Date endDate;

    private BigDecimal movieScores;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysMovieVo that = (SysMovieVo) o;
        return Objects.equals(movieName, that.movieName) && Objects.equals(movieArea, that.movieArea) && Objects.equals(movieCategoryId, that.movieCategoryId) &&Objects.equals(movieScores, that.movieScores) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieName, movieArea, movieScores, movieCategoryId, startDate, endDate);
    }

}
