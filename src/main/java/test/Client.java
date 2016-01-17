package test;

import demo.spring.service.HelloWorld;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

/**
 * Created by T430S on 2016/1/17.
 */
public class Client {

    public static void main(String[] args) {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(HelloWorld.class);
        factory.setAddress("http://192.168.1.195:8081/myframe/services/HelloWorld");
        HelloWorld service = (HelloWorld) factory.create();
        System.out.println("#############sayHi##############");
        String result = service.sayHi("tom");
        System.out.println(result);
    }

}
