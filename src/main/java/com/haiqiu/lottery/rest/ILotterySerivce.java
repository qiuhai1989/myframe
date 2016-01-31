package com.haiqiu.lottery.rest;

import com.haiqiu.lottery.entity.LotteryActivity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by T430S on 2016/1/30.
 */
@Path(value = "/lottery")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public interface ILotterySerivce {

    @POST
    @Path("/addlottery")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createLotteryActivity(LotteryActivity activity);
    @GET
    @Path("/click")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response clickLottery(@QueryParam("lottery_id") Long lotteryId);


}
