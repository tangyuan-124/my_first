package com.qfxl.cm.controller;

import com.qfxl.cm.common.response.R;
import com.qfxl.cm.domain.SysHall;
import com.qfxl.cm.service.SysHallService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 观影排座控制层
 *
 * @author: qfxl
 * @date: 2023-12-11
 */
@RestController
public class SysHallController extends BaseController {

    @Resource
    private SysHallService sysHallService;

    /**
     * 查询影院排座
     *
     * @param sysHall 排座信息
     * @return 结果
     */
    @GetMapping("/sysHall")
    public R findAllHalls(SysHall sysHall) {
        startPage();
        return getResult(sysHallService.findAllHalls(sysHall));
    }

    /**
     * 通过id查询查询影院排座
     *
     * @param hallId 排座id
     * @return 结构
     */
    @GetMapping("/sysHall/{hallId}")
    public R findHallById(@PathVariable Long hallId) {
        SysHall sysHall = new SysHall();
        sysHall.setHallId(hallId);
        return getResult(sysHallService.findHallById(sysHall));
    }

    /**
     * 添加排座
     *
     * @param sysHall 影院排座
     * @return 结果
     */
    @PostMapping("/sysHall")
    public R addHall(@Validated @RequestBody SysHall sysHall) {
        return getResult(sysHallService.addHall(sysHall));
    }

    /**
     * 更新排座
     *
     * @param sysHall 影院排座
     * @return 结果
     */
    @PutMapping("/sysHall")
    public R updateHall(@Validated @RequestBody SysHall sysHall) {
        int rows = sysHallService.updateHall(sysHall);
        return getResult(rows);
    }

    /**
     * 删除排座
     *
     * @param sysHalls 影院排座
     * @return 结果
     */
    @PostMapping("/sysHall/delete")
    public R deleteHall(@RequestBody SysHall[] sysHalls) {
        return getResult(sysHallService.deleteHall(sysHalls));
    }

}
