package com.haiqiu.lottery.service.impl;

import com.haiqiu.entity.Category;
import com.haiqiu.exception.ServiceException;
import com.haiqiu.lottery.dao.LotteryActivityMapper;
import com.haiqiu.lottery.dao.LotteryItemMapper;
import com.haiqiu.lottery.entity.LotteryActivity;
import com.haiqiu.lottery.entity.LotteryConstant;
import com.haiqiu.lottery.entity.LotteryItem;
import com.haiqiu.lottery.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by T430S on 2016/1/30.
 */
@Component("LotteryServiceImpl")
public class LotteryServiceImpl implements LotteryService {

    @Autowired
    private LotteryActivityMapper lotteryActivityMapper;
    @Autowired
    private LotteryItemMapper lotteryItemMapper;

    @Override
    @Transactional
    public Long addLotteryActivity(LotteryActivity activity) {
        int n = lotteryActivityMapper.insert(activity);
        List<LotteryItem> itemList = new ArrayList<>();
        //生成指定数量红包记录
        if(LotteryConstant.HB_TYPE_GROUP.equals(activity.getHbType())) {
            //群红包活动生成指定数量红包记录，红包金额
            if(LotteryConstant.AMT_TYPE_RAND.equals(activity.getAmType())){
                itemList = wxLotteryRand(activity);
            }else if(LotteryConstant.AMT_TYPE_NORMAL.equals(activity.getAmType())){
                itemList = wxLotteryNormal(activity);
            }

        }else if(LotteryConstant.HB_TYPE_NORMAL.equals(activity.getHbType())){
        //个人红包生成一条红包记录
            LotteryItem item = new LotteryItem();
            item.setAmount(activity.getTotalAmount());
            item.setLotteryId(activity.getId());
            item.setWishing(activity.getWishing());
            item.setIsOpen(Boolean.FALSE);
            item.setCreateTime(new Date());
            item.setModifyTime(new Date());
            itemList.add(item);

        }
        try {
            lotteryItemMapper.saveLotteryItemList(itemList);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return activity.getId();
    }

    @Override
    public LotteryItem clickLottery(Long lotteryId) {
        LotteryItem item = null;


        try {
            //判断该用户是否已经领取过本次活动红包 略

            //从红包表取出一条红包记录返回，并修改该记录状态为已使用
            item = lotteryItemMapper.getItemByLotteryId(lotteryId);
            if(item!=null){

                //修改红包状态
                item.setIsOpen(Boolean.TRUE);
                item.setModifyTime(new Date());
                lotteryItemMapper.updateLotteryItem(item);
                //4.插入一条数据到领取记录表 略
            }

            return item;
        } catch (Exception e) {
            e.printStackTrace();
            throw  new ServiceException(e.getMessage());
        }

    }

    @Override
    public void updateLotteryItem(LotteryItem item) {
        lotteryItemMapper.updateLotteryItem(item);
    }

    /**
     * 生成随机红包
     * @param activity
     * @return
     */
    private List<LotteryItem> wxLotteryRand(LotteryActivity activity){

		/*
		 * 假如10块钱的红包分成5个最低1元
			n=1 ,1~10-(5-1)*1=6 -0
			n=2 ,1~10-n1-（5-2）*1=7-n1
			n=3,1~10-n1-n2-(5-3)*1=8-n1-n2
			n=4,1~10-n1-n2-n3-(5-4)*1=9-n1-n2-n3
			n=5,1~10-n1-n2-n3-n4-(5-5)*1=10-n1-n2-n3-n4
		 */
        List<LotteryItem> items = new ArrayList<>();
        // 将单位转换为分为单位运算 降低运算发杂度
        //单个红包最少金额
        int min = 1;
        //随机安全上限
        int safeTotal ;
        //累计已分配金额
        int used=0 ;
        Random ran = new Random();
        LotteryItem item = null;
        for(int i=1,num=activity.getTotalNum(),moneySum=(int)(activity.getTotalAmount()*100);i<=num;i++){
            item = new LotteryItem();
            int  money=0;
            if(num==i){
                money = moneySum-used;
            }else{
                safeTotal = (moneySum-used-(num-i)*min)/(num-i);
                money =min+ min*ran.nextInt(safeTotal);

            }
            used+=money	;
            System.out.println("used=" + used);
            item.setAmount(money/100f);
            item.setIsOpen(Boolean.FALSE);
            item.setLotteryId(activity.getId());
            item.setWishing(activity.getWishing());
            item.setCreateTime(new Date());
            item.setModifyTime(new Date());
            items.add(item);
//            System.out.println("第"+i+"个红包，金额为"+money/100f);
        }
        items.add(item);
        return  items;
    }

    /**
     * 生成普通红包
     * @param activity
     * @return
     */
    private List<LotteryItem> wxLotteryNormal(LotteryActivity activity){

        /**
         *  将金额平均分陪
         */

        List<LotteryItem> items = new ArrayList<>();
        // 将单位转换为分为单位运算 降低运算发杂度
        //单个红包最少金额
        int min = 1;
        //随机安全上限
        int safeTotal ;
        //累计已分配金额
        int used=0 ;
        Random ran = new Random();
        LotteryItem item = null;
        for(int i=1,num=activity.getTotalNum(),moneySum=(int)(activity.getTotalAmount()*100);i<=num;i++){
            item = new LotteryItem();
            item.setAmount(moneySum/(num*100f));
            item.setIsOpen(Boolean.FALSE);
            item.setLotteryId(activity.getId());
            item.setWishing(activity.getWishing());
            item.setCreateTime(new Date());
            item.setModifyTime(new Date());
            items.add(item);
            System.out.println("第"+i+"个红包，金额为"+item.getAmount());
        }
        items.add(item);
        return  items;
    }
}
