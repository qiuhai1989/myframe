package test;

import com.haiqiu.entity.User;
import demo.spring.service.HelloWorld;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by T430S on 2016/1/17.
 */
public class SpringUsersWsClient {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-client.xml");

        HelloWorld service = ctx.getBean("helloWsClient", HelloWorld.class);

        System.out.println("#############Client getUserByName##############");
        String response = service.sayHi("Joe");
        System.out.println("Response: " + response);
    }
}
