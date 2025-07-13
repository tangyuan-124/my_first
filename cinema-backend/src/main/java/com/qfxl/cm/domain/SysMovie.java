package com.qfxl.cm.domain;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 电影实体对象
 *
 * @author: qfxl
 * @date: 2023-12-11
 */
@Data
public class SysMovie implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long movieId;

    //电影名称
    @NotBlank(message = "电影名称不能为空")
    private String movieName;

    //电影时长
    private Integer movieLength;

    //电影海报
    private String moviePoster;

    private String movieArea;

    //上映日期
    private Date releaseDate;

    //电影评分
    private BigDecimal movieScores;

    //电影总票房
    private Double movieBoxOffice;

    //电影简介
    private String movieIntroduction;

    //电影图集
    private String moviePictures;

    //电影的类别
    private List<SysMovieCategory> movieCategoryList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysMovie sysMovie = (SysMovie) o;
        return Objects.equals(movieId, sysMovie.movieId) && Objects.equals(movieName, sysMovie.movieName) && Objects.equals(movieLength, sysMovie.movieLength) && Objects.equals(moviePoster, sysMovie.moviePoster) && Objects.equals(movieArea, sysMovie.movieArea) && Objects.equals(releaseDate, sysMovie.releaseDate) && Objects.equals(movieScores, sysMovie.movieScores) && Objects.equals(movieBoxOffice, sysMovie.movieBoxOffice) && Objects.equals(movieIntroduction, sysMovie.movieIntroduction) && Objects.equals(moviePictures, sysMovie.moviePictures) && Objects.equals(movieCategoryList, sysMovie.movieCategoryList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, movieName, movieLength, moviePoster, movieArea, releaseDate, movieScores, movieBoxOffice, movieIntroduction, moviePictures, movieCategoryList);
    }

}
