package com.qfxl.cm.service.impl;

import com.qfxl.cm.domain.SysRole;
import com.qfxl.cm.mapper.SysRoleMapper;
import com.qfxl.cm.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;

/**
 * 系统角色服务层实现类
 *
 * @author: qfxl
 * @date: 2023-12-11
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> findAllRoles() {
        return sysRoleMapper.findAllRoles();
    }

    @Override
    public SysRole findRoleById(Long id) {
        return sysRoleMapper.findRoleById(id);
    }

    @Override
    public int addRole(SysRole sysRole) {
        return sysRoleMapper.addRole(sysRole);
    }

    @Override
    public int updateRole(SysRole sysRole) {
        return sysRoleMapper.updateRole(sysRole);
    }

    @Override
    public int deleteRole(Long[] ids) {
        int rows = 0;
        for (Long id : ids) {
            rows += sysRoleMapper.deleteRole(id);
        }
        return rows;
    }

    @Override
    public int allotRight(Long roleId, Long[] keys) {
        int rows = 0;
        HashSet<Long> originResources = new HashSet<>(sysRoleMapper.findAllRights(roleId));

        for (Long id : keys) {
            if (originResources.contains(id)) {
                originResources.remove(id);
            } else {
                rows += sysRoleMapper.addRight(roleId, id);
            }
        }
        for (Long id : originResources) {
            rows += sysRoleMapper.deleteRight(roleId, id);
        }
        return rows;
    }
}
