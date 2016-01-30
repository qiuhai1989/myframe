package com.haiqiu.lottery.dao;

import com.haiqiu.lottery.entity.LotteryActivity;
import org.springframework.stereotype.Component;

/**
 * Created by T430S on 2016/1/30.
 */
@Component
public interface LotteryActivityMapper {

    int insert(LotteryActivity record);

}
