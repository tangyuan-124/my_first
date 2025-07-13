package com.qfxl.cm.service;

import com.qfxl.cm.domain.SysRole;

import java.util.List;

/**
 * 系统角色服务层
 *
 * @author: qfxl
 * @date: 2023-12-11
 */
public interface SysRoleService {

    List<SysRole> findAllRoles();

    SysRole findRoleById(Long id);

    int addRole(SysRole sysRole);

    int updateRole(SysRole sysRole);

    int deleteRole(Long[] ids);

    int allotRight(Long roleId, Long[] resourceIds);
}
