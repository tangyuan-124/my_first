package com.qfxl.cm.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * 角色
 *
 * @author: qfxl
 * @date: 2023-12-11
 */
@Data
public class SysRole implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long roleId;

    //角色名称
    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    //角色描述
    @NotBlank(message = "角色描述不能为空")
    private String roleDesc;

    //角色拥有的权限，分多级权限存储，取名为children方便读取所有权限
    private List<SysResource> children;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysRole sysRole = (SysRole) o;
        return Objects.equals(roleId, sysRole.roleId) && Objects.equals(roleName, sysRole.roleName) && Objects.equals(roleDesc, sysRole.roleDesc) && Objects.equals(children, sysRole.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName, roleDesc, children);
    }

}
