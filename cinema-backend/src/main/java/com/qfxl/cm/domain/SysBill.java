package com.qfxl.cm.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 订单实体类
 *
 * @author: qfxl
 * @date: 2023-12-11
 */
@Data
public class                                                                                                                           SysBill implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long billId;

    //订单状态，0表示未支付，1表示已完成
    private Boolean payState;

    //下订单的用户id
    @NotNull(message = "下订单用户不能为空")
    private Long userId;

    //订单所属的场次
    @NotNull(message = "订单所属场次不能为空")
    private Long sessionId;

    //订单的座位，如：1排10号、A排5号
    @NotBlank(message = "订单所选座位不能为空")
    private String seats;

    private Boolean cancelState;

    private Boolean cancelRole;

    private Date createTime;

    private Date deadline;

    private Date cancelTime;

    // 用户名作模糊查询条件
    private String queryByUserName;

    // 管理员操作识别，及备注内容。管理员操作点单，或添加或修改，必须要填写备注信息，购票子系统不需要填写，并清空
    private String remark;
    // 删除状态，1：删除，0：未删除
    private Boolean delState;

    //多表连接
    private SysSession sysSession;

    private SysUser sysUser;

    // 是否是抽奖订单
    private Boolean isBlindBox;

    // 抽奖类型(可以是"电影抽奖"等)
    private String blindBoxType;

    // 抽奖内容(可以存储抽到的具体信息)
    private String blindBoxContents;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysBill sysBill = (SysBill) o;
        return Objects.equals(billId, sysBill.billId) && Objects.equals(payState, sysBill.payState) && Objects.equals(userId, sysBill.userId) && Objects.equals(sessionId, sysBill.sessionId) && Objects.equals(seats, sysBill.seats) && Objects.equals(cancelState, sysBill.cancelState) && Objects.equals(cancelRole, sysBill.cancelRole) && Objects.equals(createTime, sysBill.createTime) && Objects.equals(deadline, sysBill.deadline) && Objects.equals(cancelTime, sysBill.cancelTime) && Objects.equals(queryByUserName, sysBill.queryByUserName) && Objects.equals(remark, sysBill.remark) && Objects.equals(delState, sysBill.delState) && Objects.equals(sysSession, sysBill.sysSession) && Objects.equals(sysUser, sysBill.sysUser) && Objects.equals(isBlindBox, sysBill.isBlindBox) && Objects.equals(blindBoxType, sysBill.blindBoxType) && Objects.equals(blindBoxContents, sysBill.blindBoxContents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(billId, payState, userId, sessionId, seats, cancelState, cancelRole, createTime, deadline, cancelTime, queryByUserName, remark, delState, sysSession, sysUser, isBlindBox, blindBoxType, blindBoxContents);
    }
}
