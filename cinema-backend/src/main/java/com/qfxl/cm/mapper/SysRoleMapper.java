package com.qfxl.cm.mapper;

import com.qfxl.cm.domain.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统角色持久层
 *
 * @author: qfxl
 * @date: 2023-12-11
 */
@Mapper
public interface SysRoleMapper {

    List<SysRole> findAllRoles();

    SysRole findRoleById(Long id);

    int addRole(SysRole sysRole);

    int updateRole(SysRole sysRole);

    int deleteRole(Long id);

    int addRight(Long roleId, Long resourceId);

    int deleteRight(Long roleId, Long resourceId);

    List<Long> findAllRights(Long roleId);

}
