package com.haiqiu.serivce.impl;

import com.haiqiu.serivce.HelloSerivce;

import org.springframework.stereotype.Service;

/**
 * Created by qiu on 2015/8/31.
 */

@Service("helloSerivce")
public class HelloServiceCNImpl implements HelloSerivce{

    public HelloServiceCNImpl(){
        super();
        System.out.println("----------");
    }

	public String sayHello(String name) {
		// TODO Auto-generated method stub
		return name;
	}


}
