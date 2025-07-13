package com.qfxl.cm.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 封装用户登录输入的信息
 *
 * @author: qfxl
 * @date: 2023-12-11
 */
@Data
public class SysUserVo implements Serializable {

    private String userName;

    private String password;
}
