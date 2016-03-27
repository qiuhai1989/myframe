package com.haiqiu.web;

import com.haiqiu.entity.User;
import com.haiqiu.utils.GUID;
import com.haiqiu.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by T430S on 2016/3/26.
 */
@SuppressWarnings("restriction")
@Controller
@RequestMapping("file")
public class FileController {

    private static Logger logger = LoggerFactory
            .getLogger(FileController.class);

    @ResponseBody
    @RequestMapping(value="upload",method= RequestMethod.POST)
    public String upload2(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException{

        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断是否有文件上传
        if(multipartResolver.isMultipart(request)){
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            //取得request中的所有文件名
            Iterator<String> iterator = multiRequest.getFileNames();
            while(iterator.hasNext()){
                // 取得上传文件
                MultipartFile file = multiRequest.getFile(iterator.next());
                if(file!=null){
                    // 取得当前上传文件的文件名称
                    String fileName = file.getOriginalFilename();
                    if(StringUtils.isNotBlank(fileName)){
                        // 重命名上传后的文件名
                        String preName = GUID.next().getValue();
                        String suffixName = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
                        String newFileName = preName.concat(suffixName);
                        //定义文件上传路径
                        //如果用jetty 则用"/" 代替File.separator
                        File uploadDir = new File(request.getServletContext().getRealPath(File.separator).concat("uploadFile"));
                        if(!uploadDir.exists()){
                            uploadDir.mkdirs();
                        }
                        String newFilePath = uploadDir.getAbsolutePath().concat(File.separator).concat(newFileName);
                        logger.debug("new file path is:"+newFilePath);
                        File localFile = new File(newFilePath);
                        file.transferTo(localFile);
                        return writePrintString("",newFilePath);
                    }
                }
            }

        }

        return writePrintString("文件上传发生错误！","");
    }


    /**
     * http://localhost:8081/myframe/file/sayHello2.htmls?name=aaa&id=1
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


    /**
     * 组装特定响应报文格式
     * @param err
     * @param msg
     * @return
     */
    private String writePrintString(String err, String msg) {
        if (StringUtils.isNotBlank(msg)) {
            return "{'err':'','msg':'".concat(msg).concat("'}");
        } else {
            return "{'err':'".concat(err).concat("','msg':''}");
        }
    }
}
