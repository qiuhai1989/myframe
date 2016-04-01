package com.haiqiu.lottery.rest.impl;

import com.haiqiu.entity.JSONResult;
import com.haiqiu.exception.ServiceException;
import com.haiqiu.lottery.dto.LotteryItemDto;
import com.haiqiu.lottery.entity.LotteryActivity;
import com.haiqiu.lottery.entity.LotteryItem;
import com.haiqiu.lottery.rest.ILotterySerivce;
import com.haiqiu.lottery.service.LotteryService;
import com.haiqiu.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.util.Date;

/**
 * Created by T430S on 2016/1/30.
 */
@Component("ILotterySerivceImpl")
public class ILotterySerivceImpl implements ILotterySerivce {
    private static Logger log = LoggerFactory
            .getLogger(ILotterySerivceImpl.class);
    @Autowired
    private LotteryService lotteryService;


    @Override
    public Response createLotteryActivity(LotteryActivity activity) {

        //鉴权判断 略

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

    @Override
    public Response clickLottery(Long lotteryId) {
        //鉴权判断 略

        try{

            LotteryItem item = lotteryService.clickLottery(lotteryId);
            if(item==null){
                JSONResult<String> jsonResult = new JSONResult<>();
                jsonResult.setCode(-1);
                jsonResult.setSucceed(false);
                jsonResult.setMsg("红包已领完");
                return Response.ok(jsonResult).build();
            }

            LotteryItemDto dto = new LotteryItemDto();
            BeanUtils.copyProperties(item,dto);
            return Response.ok(new JSONResult<LotteryItemDto>(dto)).build();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw  new ServiceException(e.getMessage());
        }
    }
}
