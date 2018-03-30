package com.qixcnweb.qixian.controller;

import com.qixcnweb.qixian.constant.Constant;
import com.qixcnweb.qixian.domain.School;
import com.qixcnweb.qixian.domain.User;
import com.qixcnweb.qixian.service.SchoolService;
import com.qixcnweb.qixian.service.UserService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/** 学校相关controller
 * Created by dingxiaochi on 2018/3/29.
 */
@Controller
@RequestMapping("/school")
public class SchoolController {

    @Resource
    private SchoolService schoolService;

    @Resource
    private UserService userService;


    /**
     * 上传学校图片
     * @param file
     * @return
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/upload_images", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> uploadImages(@RequestParam("file") MultipartFile file){
        //结果集
        Map<String,Object> resultMap = new HashedMap();
       //上传到阿里云服务器,学校介绍目录
        String resultString = schoolService.uploadImages(file, Constant.SCHOOL_IMAGES);
        //如果上传正确
        if(!resultString.equals("upload error")){
            resultMap.put("status",200);
            resultMap.put("fileName",resultString);
        }else{
            resultMap.put("status",500);
            resultMap.put("message",resultString);
        }
        return resultMap;
    }


    /**
     * 学校入驻
     * @param identityImage
     * @param licenseImage
     * @return
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/enter", method = RequestMethod.POST)
    public String enter(@RequestParam("identityImage") MultipartFile identityImage, @RequestParam("licenseImage") MultipartFile licenseImage,
                        @Valid School school, BindingResult schoolBindResult, HttpServletRequest request){
        if(!schoolBindResult.hasErrors()){
            //保存法人身份证信息,上传到阿里云服务器,学校介绍目录
            String identityImageName = schoolService.uploadImages(identityImage, Constant.USER_INDENTITY);
            //保存营业执照
            String licenseImageName = schoolService.uploadImages(licenseImage, Constant.SCHOOL_LICENSE);

            //获取当前登录用户
            User sessionUser = (User)request.getSession().getAttribute("user");
            User user = userService.findUserById(sessionUser.getId());

            //保存school信息
            school.setLicense(licenseImageName);        //更新学校营业执照
            school.setUser(user);                       //关联用户
            school.setStatus(Constant.SCHOOL_WAITE);    //学校状态为0 :待审核信息
            schoolService.saveSchool(school);


            //更新用户信息
            user.setIdentity(identityImageName);        //更新用户的identity字段
            user.setType(Constant.SCHOOL_ADMIN);        //更新用户类型

            //将用户信息更新到session中
            sessionUser.setType(Constant.SCHOOL_ADMIN);
            sessionUser.setImage(identityImageName);
            request.getSession().setAttribute("user",sessionUser);

            userService.saveUser(user);

            //todo:调用远程服务发送消息
            //todo:调用远程服务发送邮件
            return "redirect:/index";
        }else{
            return "errorpage/500";
        }
    }
}
