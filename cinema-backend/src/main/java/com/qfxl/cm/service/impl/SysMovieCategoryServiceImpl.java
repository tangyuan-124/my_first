package com.qfxl.cm.service.impl;

import com.qfxl.cm.domain.SysMovieCategory;
import com.qfxl.cm.domain.SysMovieToCategory;
import com.qfxl.cm.mapper.SysMovieCategoryMapper;
import com.qfxl.cm.service.SysMovieCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 电影分类服务层实现类
 *
 * @author: qfxl
 * @date: 2023-12-11
 */
@Service
public class SysMovieCategoryServiceImpl implements SysMovieCategoryService {

    @Resource
    private SysMovieCategoryMapper sysMovieCategoryMapper;

    @Override
    public List<SysMovieCategory> findAllCategorys() {
        return sysMovieCategoryMapper.findAllCategorys();
    }

    @Override
    public SysMovieCategory findCategoryById(Long id) {
        return sysMovieCategoryMapper.findCategoryById(id);
    }

    @Override
    public int addCategory(SysMovieCategory sysMovieCategory) {
        return sysMovieCategoryMapper.addCategory(sysMovieCategory);
    }

    @Override
    public int updateCategory(SysMovieCategory sysMovieCategory) {
        return sysMovieCategoryMapper.updateCategory(sysMovieCategory);
    }

    @Override
    public int deleteCategory(Long[] ids) {
        int rows = 0;
        for (Long id : ids) {
            rows += sysMovieCategoryMapper.deleteCategory(id);
        }
        return rows;
    }


    @Override
    public int addMovieToCategory(SysMovieToCategory sysMovieToCategory) {
        return sysMovieCategoryMapper.addMovieToCategory(sysMovieToCategory);
    }

    @Override
    public int deleteMovieToCategory(SysMovieToCategory sysMovieToCategory) {
        return sysMovieCategoryMapper.deleteMovieToCategory(sysMovieToCategory);
    }

}
