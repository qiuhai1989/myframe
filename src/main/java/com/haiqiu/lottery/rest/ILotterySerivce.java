package com.haiqiu.lottery.rest;

import com.haiqiu.lottery.entity.LotteryActivity;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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

}
