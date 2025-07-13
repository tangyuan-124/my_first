package com.qfxl.cm.service.impl;

import com.qfxl.cm.domain.SysBill;
import com.qfxl.cm.domain.SysSession;
import com.qfxl.cm.service.SysBillService;
import com.qfxl.cm.service.SysLotteryService;
import com.qfxl.cm.service.SysSessionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;

@Service
public class SysLotteryServiceImpl implements SysLotteryService {

    @Resource
    private SysSessionService sysSessionService;

    @Resource
    private SysBillService sysBillService;

    @Override
    public SysBill drawMovieSession(Long userId, Integer type) {
        try {
            // 如果是谢谢参与，直接返回null
            if (type == 3) {
                return null;
            }

            // 获取所有有效场次
            List<SysSession> sessions = sysSessionService.findByVo(null);
            if (sessions == null || sessions.isEmpty()) {
                throw new RuntimeException("暂无可用场次");
            }

            // 筛选出还有空座位的场次
            List<SysSession> availableSessions = sessions.stream()
                    .filter(session ->
                            session != null
                                    && session.getSallNums() != null
                                    && session.getSeatNums() != null
                                    && session.getSysMovie() != null
                                    && session.getSallNums() < session.getSeatNums()
                    )
                    .collect(Collectors.toList());

            if (availableSessions.isEmpty()) {
                throw new RuntimeException("暂无可抽奖场次");
            }

            // 随机选择一个场次
            Random random = new Random();
            SysSession selectedSession = availableSessions.get(random.nextInt(availableSessions.size()));

            // 更新场次座位信息
            JSONObject seatsJson = JSONObject.parseObject(selectedSession.getSessionSeats());
            String availableSeat = findAvailableSeat(seatsJson);
            if (availableSeat == null) {
                throw new RuntimeException("所选场次无可用座位");
            }

            // 更新已售座位数
            selectedSession.setSallNums(selectedSession.getSallNums() + 1);
            selectedSession.setSessionSeats(seatsJson.toJSONString());
            sysSessionService.updateSession(selectedSession);

            // 创建抽奖订单
            SysBill bill = new SysBill();
            bill.setUserId(userId);
            bill.setSessionId(selectedSession.getSessionId());
            bill.setPayState(true);
            bill.setCancelState(false);
            bill.setCreateTime(new Date());
            bill.setDeadline(new Date(System.currentTimeMillis() + 15 * 60 * 1000));
            bill.setSeats(availableSeat);
            bill.setIsBlindBox(true);
            bill.setBlindBoxType(getBlindBoxType(type));
            bill.setBlindBoxContents("抽中电影:" + selectedSession.getSysMovie().getMovieName());

            // 保存订单
            Object result = sysBillService.addBill(bill);
            if (result instanceof Integer && (Integer)result <= 0) {
                return null;
            }
            if (result instanceof SysBill) {
                bill = (SysBill) result;
            }

            bill.setSysSession(selectedSession);
            return bill;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String findAvailableSeat(JSONObject seatsJson) {
        try {
            // 获取所有可用座位
            List<String> availableSeats = new ArrayList<>();
            // 遍历每一排
            for (String row : seatsJson.keySet()) {
                // 跳过第一排
                if (row.equals("1")) {
                    continue;
                }
                JSONArray rowSeats = seatsJson.getJSONArray(row);
                // 遍历该排的每个座位
                for (int i = 0; i < rowSeats.size(); i++) {
                    // 0表示座位未被占用
                    if (rowSeats.getInteger(i) == 0) {
                        availableSeats.add(row + "," + i);
                    }
                }
            }

            if (!availableSeats.isEmpty()) {
                // 随机选择一个可用座位
                Random random = new Random();
                String selectedSeat = availableSeats.get(random.nextInt(availableSeats.size()));
                String[] seatInfo = selectedSeat.split(",");
                String row = seatInfo[0];
                int col = Integer.parseInt(seatInfo[1]);

                // 更新座位状态为已占用
                JSONArray rowSeats = seatsJson.getJSONArray(row);
                rowSeats.set(col, 1); // 使用1表示已占用的座位

                // 返回JSON格式的座位信息
                JSONArray seatsArray = new JSONArray();
                seatsArray.add(row + "排" + (col + 1) + "座");
                return seatsArray.toJSONString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getBlindBoxType(Integer type) {
        switch (type) {
            case 1:
                return "随机电影";
            case 2:
                return "再抽一次";
            case 3:
                return "谢谢参与";
            default:
                return "电影抽奖";
        }
    }
}