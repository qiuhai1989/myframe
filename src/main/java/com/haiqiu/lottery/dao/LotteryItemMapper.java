package com.haiqiu.lottery.dao;

import com.haiqiu.lottery.entity.LotteryItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by T430S on 2016/1/30.
 */
@Component
public interface LotteryItemMapper {

    public void saveLotteryItemList(@Param("list")List<LotteryItem> list);

}


