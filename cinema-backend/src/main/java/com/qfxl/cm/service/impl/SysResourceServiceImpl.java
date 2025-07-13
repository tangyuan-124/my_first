package com.qfxl.cm.service.impl;

import com.qfxl.cm.domain.SysResource;
import com.qfxl.cm.mapper.SysResourceMapper;
import com.qfxl.cm.service.SysResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统资源服务层实现类
 *
 * @author: qfxl
 * @date: 2023-12-11
 */
@Slf4j
@Service
public class SysResourceServiceImpl implements SysResourceService {

    @Resource
    private SysResourceMapper sysResourceMapper;

    @Override
    public List<SysResource> findAllResources() {
        return sysResourceMapper.findAllResources();
    }

    @Override
    public List<SysResource> findWithChildren() {
        return sysResourceMapper.findWithChildren();
    }

    @Override
    public List<SysResource> findAllWithAllChildren() {
        return sysResourceMapper.findAllWithAllChildren();
    }

    @Override
    public SysResource findResourceById(Long id) {
        return sysResourceMapper.findResourceById(id);
    }

    @Override
    public int addResource(SysResource sysResource) {
        if (sysResource.getParentId() == 0) {
            sysResource.setLevel(1);
        } else {
            SysResource parent = this.findResourceById(sysResource.getParentId());
            if (parent != null) {
                sysResource.setLevel(parent.getLevel() + 1);
            }
        }
        return sysResourceMapper.addResource(sysResource);
    }

    @Override
    public int updateResource(SysResource sysResource) {
        if (sysResource.getParentId() == 0) {
            sysResource.setLevel(1);
        } else {
            SysResource parent = this.findResourceById(sysResource.getParentId());
            if (parent != null) {
                sysResource.setLevel(parent.getLevel() + 1);
            }
        }
        log.debug(sysResource.toString());
        return sysResourceMapper.updateResource(sysResource);
    }

    @Override
    public int deleteResource(Long[] ids) {
        int rows = 0;
        for (Long id : ids) {
            rows += sysResourceMapper.deleteResource(id);
        }
        return rows;
    }
}
