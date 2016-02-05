package com.haiqiu.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by T430S on 2016/2/5.
 */
@Component("anotherSchedule")
public class AnotherSchedule {
    private static Logger log = LoggerFactory.getLogger(MonitorSchedule.class);
    public void doSomeThing(){
        log.info("**************doSomeThing start**********************");
        System.out.println("我是不是该做点什么！");
        log.info("**************doSomeThing end**********************");
    }

}
