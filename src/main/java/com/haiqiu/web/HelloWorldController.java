package com.haiqiu.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import java.util.Properties;

import com.alibaba.druid.pool.vendor.SybaseExceptionSorter;
import com.haiqiu.entity.User;
import com.haiqiu.serivce.HelloSerivce;
import com.haiqiu.serivce.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
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
@SessionAttributes(value = {"user"})
public class HelloWorldController {
	private static Logger log = LoggerFactory
			.getLogger(HelloWorldController.class);
	@Autowired
    private HelloSerivce helloSerivce;
	
	@Autowired
	private UserService userService;
	@Value("#{p_common['max.count']}")
	private String maxCount;
	@Value("#{p_common}")
	private Properties properties;

	//http://localhost:8081/myframe/hello/sayHello/1.htmls?name=aa&age=15
	/**
	 * 访问数据模型 通过ModelAndView
	 * @param name
	 * @param age
	 * @param id
	 * @return
	 */
    @RequestMapping(value = "/sayHello/{id}",method=RequestMethod.GET,params="name")
    public ModelAndView sayHello(@RequestParam("name") String name,@RequestParam(name="age",required=true) String age,@PathVariable int id){
    	log.info("name="+name+",age="+age);
       String info = helloSerivce.sayHello(name);
        System.out.println(info);
        System.out.println("id="+id);
        System.out.println("age="+age);
        System.out.println("**************");
        ModelAndView model = new ModelAndView("/user/success");
        model.addObject("info", info);

		System.out.println("maxCount="+maxCount);
		System.out.println(properties.getProperty("max.count"));

        return model;
    }
    
    /**
     * http://localhost:8081/myframe/hello/sayHello2.htmls?name=aaa&id=1
     * 访问数据模型通过@ModelAttribute 
     * Spring MVC将HTTP请求数据绑定到user入参中，然后再将user对象添加到数据模型中
     * @param user
     * @return
     */
    @RequestMapping(value="/sayHello2")
    public  String sayHello2(@ModelAttribute("user") User user){
    	user.setPhone("18664315576");
    	return "/user/hello";
    }
    
/*    @ModelAttribute("user")
    public User getUser(){
    	User user = new User();
    	user.setId(1001); 
    	return user;
    }*/
    //http://localhost:8081/myframe/hello/handle71.htmls
    @RequestMapping(value = "/handle71")
	public String  handle71(@ModelAttribute("user") User user){
		user.setName("John");
		return "redirect:/hello/handle72.htmls";
	}

	@RequestMapping(value = "/handle72")
	public String  handle72(ModelMap modelMap,SessionStatus sessionStatus){
		User user = (User)modelMap.get("user");
		if(user != null){ 
			user.setName("Jetty");
			sessionStatus.setComplete();
		}
		return "/user/hello";
	}
    
    /**
     * 如果希望在多个请求之间共用某个模型属性数据，则可以在控制器类标注一个@SessionAttributes，Spring MVC会将模型中对应的属性暂存到HttpSession中
     * @param modelMap
     * @return
     */
    @RequestMapping(value="/getSessionAttributes")
    public  String SessionAttributes(ModelMap modelMap,SessionStatus sessionStatus){

    	User user = (User) modelMap.get("user");
    	if(user!=null){
    		user.setName("clear");
    		sessionStatus.setComplete();
    	}
    	
    	return "/user/hello";
    }
    


/*    @RequestMapping(value = "/sayHello3")
    public String  sayHello3(ModelMap modelMap){
         modelMap.addAttribute("testAttr","value1");
         User user = (User)modelMap.get("user");
         user.sete("tom");		
         return "/user/hello";
    }*/

    
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


	@RequestMapping(value="/addUser",method = RequestMethod.GET)
	public String toAddUser( User user){
		return "/user/addUser";
	}


	@RequestMapping(value="/addUser",method = RequestMethod.POST)
	@ResponseBody
	public String addUser(@RequestBody User user){
		System.out.println(user);
		return "success";
	}

	@RequestMapping(value="/addUser2",method = RequestMethod.POST)
	@ResponseBody
	public String addUser2(User user){
		System.out.println(user);
		return "success";
	}

	@RequestMapping(value="addUser3",method = RequestMethod.POST)
	@ResponseBody
	public String addUser3(@ModelAttribute("user")User user){
		System.out.println(user);
		return "success";
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
