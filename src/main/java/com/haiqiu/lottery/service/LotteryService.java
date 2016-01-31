package com.haiqiu.lottery.service;

import com.haiqiu.lottery.entity.LotteryActivity;
import com.haiqiu.lottery.entity.LotteryItem;

/**
 * Created by T430S on 2016/1/30.
 */
public interface LotteryService {

    public Long addLotteryActivity(LotteryActivity activity);

    public LotteryItem clickLottery(Long lotteryId);

    public void updateLotteryItem(LotteryItem item);

}
