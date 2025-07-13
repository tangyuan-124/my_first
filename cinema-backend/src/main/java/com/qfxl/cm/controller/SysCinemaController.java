package com.qfxl.cm.controller;

import com.qfxl.cm.common.response.R;
import com.qfxl.cm.domain.SysCinema;
import com.qfxl.cm.domain.SysSession;
import com.qfxl.cm.service.SysCinemaService;
import com.qfxl.cm.service.SysSessionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * 电影院管理控制层
 *
 * @author: qfxl
 * @date: 2023-12-11
 */
@RestController
public class SysCinemaController extends BaseController {

    @Resource
    private SysCinemaService sysCinemaService;

    @Resource
    private SysSessionService sysSessionService;

    /**
     * 查询影院
     *
     * @return 影院
     */
    @GetMapping("/sysCinema")
    public R findCinema() {
        return getResult(sysCinemaService.findCinema());
    }

    /**
     * 更新影院信息
     *
     * @param sysCinema 影院信息
     * @return 结果
     */
    @PutMapping("/sysCinema/update")
    public R updateCinema(@Validated @RequestBody SysCinema sysCinema) {
        return getResult(sysCinemaService.updateCinema(sysCinema));
    }

    /**
     * 通过id查询影院
     *
     * @param cinemaId 电影院id
     * @param movieId  电影id
     * @return 影院
     */
    @GetMapping(value = {"/sysCinema/find/{cinemaId}/{movieId}", "/sysCinema/find/{cinemaId}"})
    public R findCinemaById(@PathVariable Long cinemaId, @PathVariable(required = false) Long movieId) {
        SysCinema cinema = sysCinemaService.findCinemaById(cinemaId);
        if (movieId == null || movieId == 0) {
            movieId = cinema.getSysMovieList().size() > 0 ? cinema.getSysMovieList().get(0).getMovieId() : 0;
        }
        List<SysSession> sessions = null;
        if (movieId != null && movieId != 0) {
            sessions = sysSessionService.findSessionByMovieId(movieId);
        }

        HashMap<String, Object> response = new HashMap<>();
        response.put("cinema", cinema);
        response.put("sessions", sessions);
        return getResult(response);
    }

}
