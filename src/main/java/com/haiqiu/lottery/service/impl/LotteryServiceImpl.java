package com.haiqiu.lottery.service.impl;

import com.haiqiu.lottery.dao.LotteryActivityMapper;
import com.haiqiu.lottery.entity.LotteryActivity;
import com.haiqiu.lottery.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by T430S on 2016/1/30.
 */
@Component("LotteryServiceImpl")
public class LotteryServiceImpl implements LotteryService {

    @Autowired
    private LotteryActivityMapper lotteryActivityMapper;

    @Override
    @Transactional
    public Long addLotteryActivity(LotteryActivity activity) {
        int n = lotteryActivityMapper.insert(activity);
        return activity.getId();
    }
}
