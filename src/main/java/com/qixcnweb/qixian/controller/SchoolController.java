package com.qixcnweb.qixian.controller;

import com.qixcnweb.qixian.constant.Constant;
import com.qixcnweb.qixian.domain.School;
import com.qixcnweb.qixian.domain.User;
import com.qixcnweb.qixian.remote.RabbitFeignClient;
import com.qixcnweb.qixian.service.SchoolService;
import com.qixcnweb.qixian.service.UserService;
import com.qixcnweb.qixian.utils.FileUploadUtils;
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
import java.util.ArrayList;
import java.util.Arrays;
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

    @Resource
    private RabbitFeignClient rabbitFeignClient;

    @Resource
    private FileUploadUtils fileUploadUtils;


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

        //获取当前登录用户
        User sessionUser = (User)request.getSession().getAttribute("user");
        User user = userService.findUserById(sessionUser.getId());

        if(!schoolBindResult.hasErrors()){
            //保存法人身份证信息,上传到阿里云服务器,学校介绍目录
            if(identityImage!=null && !"".equals(identityImage.getOriginalFilename())){
                String identityImageName = schoolService.uploadImages(identityImage, Constant.USER_INDENTITY);
                user.setIdentity(identityImageName);        //更新用户的identity字段
                sessionUser.setIdentity(identityImageName); //更新session中存储的用户信息
            }
            //保存营业执照
            if(licenseImage!=null && !"".equals(licenseImage.getOriginalFilename())){
                String licenseImageName = schoolService.uploadImages(licenseImage, Constant.SCHOOL_LICENSE);
                school.setLicense(licenseImageName);
            }

            //保存school信息
            school.setUser(user);                       //关联用户
            school.setStatus(Constant.SCHOOL_WAITE);    //学校状态为0 :待审核信息
            schoolService.saveSchool(school);


            //更新用户信息
            user.setType(Constant.SCHOOL_ADMIN);        //更新用户类型

            //将用户信息更新到session中
            sessionUser.setType(Constant.SCHOOL_ADMIN);
            request.getSession().setAttribute("user",sessionUser);
            userService.saveUser(user);

            //调用rabbitmq的远程服务发送消息到 "qixian.mail"队列中,mail服务收到消息之后会发送邮件
            rabbitFeignClient.sendMessageWithoutExchange("qixian.mail",user);

            return "redirect:/index";
        }else{
            return "errorpage/500";
        }
    }

    /**
     * 跳转到学校详情页面
     * @param schoolId
     * @return
     */
    @GetMapping("/index/{schoolId}")
    public String toSchoolPage(HttpServletRequest request, Map<String,Object> resultMap, @PathVariable Integer schoolId){
        //获取当前登录用户
        User user = (User) request.getSession().getAttribute("user");
        //根据ID获取学校信息
        School school = schoolService.findSchoolById(schoolId);
        //获取学校图片,并且把图片换成能访问的OSS url
        String schoolMainImg = "";
        Map<String,String> schoolImgMap = new HashedMap();
        if(school.getImage()!=null && !"".equals(school.getImage())){
            String image = school.getImage();
            List<String> imageList = Arrays.asList(image.split(","));
            //第一张图片设置为主图,其他的图片为页面图片列表的图片
            for(String imageName : imageList){
                if(imageList.get(0).equals(imageName)){
                    schoolMainImg = fileUploadUtils.getFileUrl(imageName,1000*60*60*3,Constant.OSS_STYLE_SCHOOL_MAIN);
                }else{
                    schoolImgMap.put(imageName,fileUploadUtils.getFileUrl(imageName,1000*60*60*3));
                }
            }
        }
        resultMap.put("user",user);
        resultMap.put("school",school);
        resultMap.put("schoolMainImg",schoolMainImg);
        resultMap.put("schoolImgMap",schoolImgMap);
        return "school/school";
    }

    /**
     * 跳转到编辑课程页面
     * @return
     */
    @GetMapping("/edit_lesson_page")
    @PreAuthorize("isAuthenticated()")
    public String schoolEditLessonPage(HttpServletRequest request, Map<String,Object> resultMap){
        User user = (User) request.getSession().getAttribute("user");
        resultMap.put("user",user);
        return "school/school_edit_lesson";
    }

    /**
     * 跳转到编辑教师页面
     * @return
     */
    @GetMapping("/edit_teacher_page")
    @PreAuthorize("isAuthenticated()")
    public String schoolEditTeacherPage(HttpServletRequest request, Map<String,Object> resultMap){
        User user = (User) request.getSession().getAttribute("user");
        resultMap.put("user",user);
        return "school/school_edit_teacher";
    }
}
