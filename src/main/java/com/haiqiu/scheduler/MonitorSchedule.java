package com.haiqiu.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by T430S on 2016/2/5.
 */
@Component("monitorSchedule")
public class MonitorSchedule {

    private static Logger log = LoggerFactory.getLogger(MonitorSchedule.class);

    public void greet(){
        log.info("**************greet start**********************");
        System.out.println("你好世界！");
        log.info("**************greet end**********************");
    }

}
