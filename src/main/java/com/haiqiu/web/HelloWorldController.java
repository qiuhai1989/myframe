package com.haiqiu.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

import com.haiqiu.entity.User;
import com.haiqiu.serivce.HelloSerivce;
import com.haiqiu.serivce.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by qiu on 2015/9/4.
 */
@Controller
@RequestMapping("/hello")
public class HelloWorldController {

	@Autowired
    private HelloSerivce helloSerivce;
	
	@Autowired
	private UserService userService;
	
	
	//http://localhost:8081/myframe/hello/sayHello/1.htmls?name=aa&age=15
    @RequestMapping(value = "/sayHello/{id}",method=RequestMethod.GET,params="name")
    public ModelAndView sayHello(@RequestParam("name") String name,@RequestParam(name="age",required=true) String age,@PathVariable int id){
       String info = helloSerivce.sayHello(name);
        System.out.println(info);
        System.out.println("id="+id);
        System.out.println("age="+age);
        System.out.println("**************");
        ModelAndView model = new ModelAndView("/user/success");
        model.addObject("info", info);
        return model;
    }
    
    //http://localhost:8081/myframe/hello/getAllUser.htmls
    @RequestMapping(value="/getAllUser")
    @ResponseBody
    public List<User> getAllUser(@CookieValue("JSESSIONID") String sessionId,@RequestHeader("Accept-Language") String accpetLanguage,@RequestHeader("Host") String host){
    	System.out.println("sessionId="+sessionId);
    	System.out.println("accpetLanguage="+accpetLanguage);
    	System.out.println("host="+host);
    	return userService.getAllUser();
    }
    
    //http://localhost:8081/myframe/hello/pojo.htmls?name=aa&id=11
    @RequestMapping(value="/pojo")
    @ResponseBody
    public User testPojo(User user){
    	return user;
    }
    
    
    @RequestMapping(value="/servletApi")
    @ResponseBody
    public void testServletApi(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
    	
    	System.out.println(request.getLocalAddr());
    	System.out.println(session);
    	response.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html; charset=utf-8");
    	PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write("哈哈");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			writer.close();
		}
    }
    
    
    

}
