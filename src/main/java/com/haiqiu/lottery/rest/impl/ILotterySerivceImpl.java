package com.haiqiu.lottery.rest.impl;

import com.haiqiu.entity.JSONResult;
import com.haiqiu.exception.ServiceException;
import com.haiqiu.lottery.entity.LotteryActivity;
import com.haiqiu.lottery.rest.ILotterySerivce;
import com.haiqiu.lottery.service.LotteryService;
import com.haiqiu.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;

/**
 * Created by T430S on 2016/1/30.
 */
@Component("ILotterySerivceImpl")
public class ILotterySerivceImpl implements ILotterySerivce {
    @Autowired
    private LotteryService lotteryService;

    @Override
    public Response createLotteryActivity(LotteryActivity activity) {

        if(StringUtils.isEmpty(activity.getHbType())){
            throw  new ServiceException("红包类型不能为空");
        }
        if(activity.getTotalAmount()==null||activity.getTotalAmount().floatValue()<0){
            throw  new ServiceException("红包金额必须大于0");
        }
        if("GROUP".equals(activity.getHbType())){
            if(!(activity.getTotalNum().intValue()>0)){
                throw  new ServiceException("红包数量必须大于1");
            }
            if(StringUtils.isEmpty(activity.getAmType())){
                throw  new ServiceException("红包金额设置方式必须指定");
            }
            if(activity.getTotalAmount()>5000f){
                throw  new ServiceException("红包金额不能超过5000");
            }
        }else if("NORMAL".equals(activity.getHbType())){
            if(activity.getTotalAmount()>200f){
                throw  new ServiceException("红包金额不能超过200");
            }
        }

        Long lotteryId =  lotteryService.addLotteryActivity(activity);
        return Response.ok(new JSONResult<Long>(lotteryId)).build();
    }
}
