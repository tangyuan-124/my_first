package com.qfxl.cm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * 场次实体类
 *
 * @author: qfxl
 * @date: 2023-12-11
 */
@Data
public class SysSession implements Serializable {

    private static final Long serialVersionUID = 1L;

    //场次编号
    private Long sessionId;

    //影厅编号
    @NotNull(message = "场次所在影厅不能为空")
    private Long hallId;

    //该场次语言版本
    @NotBlank(message = "场次电影语言版本不能为空")
    private String languageVersion;

    // 电影编号
    @NotNull(message = "场次安排电影不能为空")
    private Long movieId;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "HH:mm")
    private String playTime;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "HH:mm")
    private String endTime;

    // 截止时间，此时间之前不可删不可改电影、影厅信息
    private String deadline;

    // 场次日期
    @NotNull(message = "场次日期不能为空")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private LocalDate sessionDate;

    // 场次票价
    @NotNull(message = "场次票价不能为空")
    @Size(min = 0, message = "场次票价不能为负数")
    private Double sessionPrice;

    // 场次提示
    private String sessionTips;

    // 场次座位信息
    @NotBlank(message = "场次座位信息不能为空")
    private String sessionSeats;

    private Integer seatNums;

    // 已售座位数
    private Integer sallNums;

    private SysHall sysHall;

    private SysMovie sysMovie;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysSession that = (SysSession) o;
        return Objects.equals(sessionId, that.sessionId) && Objects.equals(hallId, that.hallId) && Objects.equals(languageVersion, that.languageVersion) && Objects.equals(movieId, that.movieId) && Objects.equals(playTime, that.playTime) && Objects.equals(endTime, that.endTime) && Objects.equals(deadline, that.deadline) && Objects.equals(sessionDate, that.sessionDate) && Objects.equals(sessionPrice, that.sessionPrice) && Objects.equals(sessionTips, that.sessionTips) && Objects.equals(sessionSeats, that.sessionSeats) && Objects.equals(seatNums, that.seatNums) && Objects.equals(sallNums, that.sallNums) && Objects.equals(sysHall, that.sysHall) && Objects.equals(sysMovie, that.sysMovie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, hallId, languageVersion, movieId, playTime, endTime, deadline, sessionDate, sessionPrice, sessionTips, sessionSeats, seatNums, sallNums, sysHall, sysMovie);
    }

}
