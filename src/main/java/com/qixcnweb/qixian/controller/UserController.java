package com.qixcnweb.qixian.controller;

import com.qixcnweb.qixian.domain.User;
import com.qixcnweb.qixian.service.UserService;
import com.qixcnweb.qixian.utils.ImageUtils;
import com.qixcnweb.qixian.utils.JsonUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by dingxiaochi on 2018/3/18.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private ImageUtils imageUtils;

    @Resource
    private JsonUtils jsonUtils;

    /**
     * 通过用户账号查找用户
     * @param name
     * @return
     */
    @GetMapping("/findUserByName")
    @ResponseBody
    public String findUserByName(String name){
        User user = userService.findUserByName(name);
        if(user!=null){
            return "false";
        }else{
            return "true";
        }
    }

    /**
     * 通过电话号码查询用户
     * @param phone
     * @return
     */
    @GetMapping("/findUserByPhone")
    @ResponseBody
    public String findUserByPhone(String phone){
        User user = userService.findUserByPhone(phone);
        if(user!=null){
            return "false";
        }else{
            return "true";
        }
    }

    /**
     * 通过电子邮箱查询用户
     * @param email
     * @return
     */
    @GetMapping("/findUserByEmail")
    @ResponseBody
    public String findUserByEmail(String email){
        User user = userService.findUserByEmail(email);
        if(user!=null){
            return "false";
        }else{
            return "true";
        }
    }

    /**
     * 更新用户头像
     * @param multipartFile
     * @return
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/update_user_head", method = RequestMethod.POST)
    @ResponseBody
    public Map updateUserHead(@RequestParam("file") MultipartFile multipartFile, String data, HttpServletRequest request) throws IOException {

        Map<String,Object> resultMap = new HashedMap();
        //获取当前用户
        User user = (User) request.getSession().getAttribute("user");


        //得到剪裁的坐标和宽高,将图片裁剪
        Map<String, Object> json2Map = jsonUtils.json2Map(data);


        String x1 = json2Map.get("x").toString();
        String y1 = json2Map.get("y").toString();
        String width1 = json2Map.get("width").toString();
        String height1 = json2Map.get("height").toString();


        //todo:这里有BUG  如果字符串不包含小数点就会报异常
        Integer x = Integer.parseInt(x1.substring(0,x1.indexOf(".")));                  // 剪裁X坐标
        Integer y = Integer.parseInt(y1.substring(0,y1.indexOf(".")));                  //剪裁Y坐标
        Integer width = Integer.parseInt(width1.substring(0,width1.indexOf(".")));      //剪裁宽
        Integer height = Integer.parseInt(height1.substring(0,height1.indexOf(".")));   //剪裁高

        String filename = multipartFile.getOriginalFilename();
        String fileSuffix = filename.substring(filename.lastIndexOf(".")+1,filename.length());      //转换图片格式



        File file = imageUtils.cutImage(multipartFile.getInputStream(),fileSuffix, x, y, width, height);
        //将图片上传,并且更新用户image字段
        String newFileName = userService.updateUserHead(file, fileSuffix, user);

        //todo:还需要返回新图片的访问地址
        resultMap.put("state",200);
        resultMap.put("result","这个放心图片的访问地址");
        return resultMap;
    }
}
