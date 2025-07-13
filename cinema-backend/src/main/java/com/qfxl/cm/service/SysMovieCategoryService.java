package com.qfxl.cm.service;

import com.qfxl.cm.domain.SysMovieCategory;
import com.qfxl.cm.domain.SysMovieToCategory;

import java.util.List;

/**
 * 电影分类服务层
 *
 * @author: qfxl
 * @date: 2023-12-11
 */
public interface SysMovieCategoryService {

    List<SysMovieCategory> findAllCategorys();

    SysMovieCategory findCategoryById(Long id);

    int addCategory(SysMovieCategory sysMovieCategory);

    int updateCategory(SysMovieCategory sysMovieCategory);

    int deleteCategory(Long[] ids);

    int addMovieToCategory(SysMovieToCategory sysMovieToCategory);

    int deleteMovieToCategory(SysMovieToCategory sysMovieToCategory);


}
