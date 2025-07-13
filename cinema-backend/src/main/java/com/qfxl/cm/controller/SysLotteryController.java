package com.qfxl.cm.controller;

import com.qfxl.cm.common.response.R;
import com.qfxl.cm.domain.SysBill;
import com.qfxl.cm.service.SysLotteryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class SysLotteryController extends BaseController {

    @Resource
    private SysLotteryService sysLotteryService;

    private Map<Long, Boolean> userLotteryStatus = new ConcurrentHashMap<>();

    /**
     * 抽奖接口
     * @param userId 用户ID
     * @param type 抽奖类型 (1:普通抽奖 2:VIP抽奖 3:限时抽奖)
     * @return 抽奖结果
     */
    @PostMapping("/lottery/drawMovie")
    public R drawMovie(@RequestParam Long userId, @RequestParam Integer type) {
        try {
            // 检查用户是否已经抽过奖
            if (userLotteryStatus.containsKey(userId)) {
                return R.error("您已经参与过抽奖活动了");
            }

            SysBill result = sysLotteryService.drawMovieSession(userId, type);
            if (result == null) {
                if (type != null && type == 3) {
                    return R.success("谢谢参与");
                }
                return R.error("抽奖失败，请稍后重试");
            }
            if (result.getBillId() == null || result.getSessionId() == null) {
                return R.error("订单创建失败");
            }
            // 标记用户已抽奖
            userLotteryStatus.put(userId, true);
            return R.success("抽奖成功", result);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }
}