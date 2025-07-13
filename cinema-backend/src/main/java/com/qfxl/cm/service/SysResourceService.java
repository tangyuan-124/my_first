package com.qfxl.cm.service;

import com.qfxl.cm.domain.SysResource;

import java.util.List;

/**
 * 系统资源服务层
 *
 * @author: qfxl
 * @date: 2023-12-11
 */
public interface SysResourceService {

    List<SysResource> findAllResources();

    List<SysResource> findWithChildren();

    List<SysResource> findAllWithAllChildren();

    SysResource findResourceById(Long id);

    int addResource(SysResource sysResource);

    int updateResource(SysResource sysResource);

    int deleteResource(Long[] ids);
}
